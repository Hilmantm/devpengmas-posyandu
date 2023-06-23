package id.kodesumsi.telkompengmas.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import id.kodesumsi.telkompengmas.BuildConfig
import id.kodesumsi.telkompengmas.data.source.network.NetworkService
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSource
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object ApiConfig {

    @Provides
    @Singleton
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun providesChuckerCollector(
        @ApplicationContext ctx: Context
    ): ChuckerCollector {
        return ChuckerCollector(
            context = ctx,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(
        @ApplicationContext ctx: Context,
        collector: ChuckerCollector
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = ctx)
            .collector(collector = collector)
            .maxContentLength(length = 250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(
                enable = true
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        @ApplicationContext ctx: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .callTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(5, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
            .callTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun providesNetworkService(
        baseUrl: String,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        networkService: NetworkService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(networkService)
    }

}