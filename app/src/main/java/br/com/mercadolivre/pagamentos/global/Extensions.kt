package br.com.mercadolivre.pagamentos.global

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.mercadolivre.pagamentos.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context).load(imageUrl).apply(RequestOptions().placeholder(R.mipmap.ic_launcher)).into(this)
}

fun Throwable?.getErrorMessage() = when (this) {
    is SocketTimeoutException -> R.string.no_connection
    is UnknownHostException -> R.string.no_connection
    else -> R.string.generic_error
}

inline fun FragmentManager.transaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            .func()
            .commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transaction { replace(frameId, fragment).addToBackStack(null) }
}