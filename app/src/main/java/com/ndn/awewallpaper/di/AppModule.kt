package com.ndn.awewallpaper.di

import android.app.Application
import android.content.res.Resources
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefs
import com.ndn.awewallpaper.data.source.local.sharedprf.SharedPrefsImpl
import com.ndn.awewallpaper.data.source.remote.api.middleware.BooleanAdapter
import com.ndn.awewallpaper.data.source.remote.api.middleware.DoubleAdapter
import com.ndn.awewallpaper.data.source.remote.api.middleware.IntegerAdapter
import com.ndn.awewallpaper.utils.rxAndroid.BaseSchedulerProvider
import com.ndn.awewallpaper.utils.rxAndroid.SchedulerProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val appModule = module {
    single { provideResources(androidApplication()) }
    single { provideSharedPrefsApi(androidApplication()) }
    single { provideBaseSchedulerProvider() }
    single { provideGson() }
}

fun provideResources(app: Application): Resources {
    return app.resources
}

fun provideSharedPrefsApi(app: Application): SharedPrefs {
    return SharedPrefsImpl(app)
}

fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
    return SchedulerProvider()
}

fun provideGson(): Gson {
    val booleanAdapter = BooleanAdapter()
    val integerAdapter = IntegerAdapter()
    val doubleAdapter = DoubleAdapter()
    return GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, booleanAdapter)
            .registerTypeAdapter(Int::class.java, integerAdapter)
            .registerTypeAdapter(Double::class.java, doubleAdapter)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
}
