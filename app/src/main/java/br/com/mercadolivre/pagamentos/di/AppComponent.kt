package br.com.mercadolivre.pagamentos.di

import br.com.mercadolivre.pagamentos.features.MainViewModel
import br.com.mercadolivre.pagamentos.features.amount.AmountViewModel
import br.com.mercadolivre.pagamentos.features.cardissuers.CardViewModel
import br.com.mercadolivre.pagamentos.features.installments.InstallmentsViewModel
import br.com.mercadolivre.pagamentos.features.paymentmethod.PaymentMethodViewModel
import br.com.mercadolivre.pagamentos.repository.MlRepositoryImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anderson on 21/03/2018
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(target: MlRepositoryImpl)
    fun inject(target: CardViewModel)
    fun inject(target: AmountViewModel)
    fun inject(target: PaymentMethodViewModel)
    fun inject(target: InstallmentsViewModel)
    fun inject(target: MainViewModel)
}