package br.com.mercadolivre.pagamentos.di

import br.com.mercadolivre.pagamentos.repository.MlRepositoryImpl
import br.com.mercadolivre.pagamentos.ui.MainViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Anderson on 21/03/2018
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(target: MlRepositoryImpl)
    fun inject(target: MainViewModel)
}