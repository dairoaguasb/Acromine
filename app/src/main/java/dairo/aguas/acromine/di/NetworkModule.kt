package dairo.aguas.acromine.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dairo.aguas.acromine.BuildConfig
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Dairo Aguas B on 27/10/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun coroutineDispatcherProvider() = Dispatchers.IO

    @Provides
    @Singleton
    fun moshiProvider(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun httpInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun okHttpProvider(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .dispatcher(Dispatcher().apply { maxRequests = MAX_REQUEST })
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun retrofitProvider(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }
}

private const val MAX_REQUEST = 1
private const val TIMEOUT = 30L
private const val BASE_URL = "http://www.nactem.ac.uk/software/"
