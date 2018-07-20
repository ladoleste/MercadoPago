package br.com.mercadolivre.pagamentos.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Anderson on 14/02/2018.
 */
open class BaseViewModel : ViewModel() {

    protected val cDispose = CompositeDisposable()

    fun onStop() {
        cDispose.clear()
    }
}