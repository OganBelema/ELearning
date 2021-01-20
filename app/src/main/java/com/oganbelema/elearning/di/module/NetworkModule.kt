package com.oganbelema.elearning.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oganbelema.elearning.BuildConfig
import com.oganbelema.elearning.di.qualifier.ApplicationContext
import com.oganbelema.elearning.di.scope.ApplicationScope
import com.oganbelema.elearning.util.NetworkUtil
import com.oganbelema.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [ApplicationContextModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        var interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.i(it)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @ApplicationScope
    internal fun providesCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10*1000*1000)
    }

    @Provides
    @ApplicationScope
    internal fun providesCacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @ApplicationScope
    internal fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                                     cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(httpLoggingInterceptor)
        client.cache(cache)
        client.connectTimeout(5, TimeUnit.MINUTES)
        client.readTimeout(5, TimeUnit.MINUTES)
        return client.build()
    }

    @Provides
    @ApplicationScope
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    }

    @Provides
    @ApplicationScope
    internal fun provideAPIService(retrofit: Retrofit): Api {
        return retrofit.create<Api>(Api::class.java)
    }

}