package br.com.mercadolivre.pagamentos.di

import android.content.Context
import android.content.SharedPreferences
import br.com.mercadolivre.pagamentos.BuildConfig
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.global.MlApplication.Companion.apiUrl
import br.com.mercadolivre.pagamentos.repository.MlApiService
import br.com.mercadolivre.pagamentos.repository.MlRepository
import br.com.mercadolivre.pagamentos.repository.MlRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Anderson on 21/03/2018
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    fun provideLogger(): HttpLoggingInterceptor {

        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { mensagem -> Timber.tag("OkHttp").d(mensagem); })
        logger.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
        return logger
    }

    @Provides
    fun provideQueryInterceptor() = Interceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("public_key", BuildConfig.PUBLIC_KEY).build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttp(logger: HttpLoggingInterceptor, queryInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(queryInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApi(gson: Gson, client: OkHttpClient): MlApiService {

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        return retrofit.create(MlApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(): MlRepository = MlRepositoryImpl()

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences {
        return MlApplication.instance.getSharedPreferences("MercadoPagoPreferences", Context.MODE_PRIVATE)!!
    }

}