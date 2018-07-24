package br.com.mercadolivre.pagamentos.global

import android.app.Application
import br.com.mercadolivre.pagamentos.BuildConfig
import br.com.mercadolivre.pagamentos.di.AppComponent
import br.com.mercadolivre.pagamentos.di.AppModule
import br.com.mercadolivre.pagamentos.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import timber.log.Timber

class MlApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(if (BuildConfig.DEBUG) DebugLog() else ReleaseLog())
        instance = this
        Stetho.initializeWithDefaults(this)
        component = DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()
    }

    companion object {

        lateinit var instance: Application
            private set

        lateinit var component: AppComponent
            private set

        var apiUrl: String = BuildConfig.API_URL
    }
}