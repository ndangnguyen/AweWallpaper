package com.ndn.awewallpaper.utils.extension

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import com.ndn.awewallpaper.utils.rxAndroid.BaseSchedulerProvider

/**
 * Use SchedulerProvider configuration for Completable
 */
fun Completable.async(scheduler: BaseSchedulerProvider): Completable =
        this.subscribeOn(scheduler.io()).observeOn(scheduler.ui())

/**
 * Use SchedulerProvider configuration for Single
 */
fun <T> Single<T>.async(scheduler: BaseSchedulerProvider): Single<T> =
        this.subscribeOn(scheduler.io()).observeOn(scheduler.ui())

/**
 * Use SchedulerProvider configuration for Observable
 */
fun <T> Observable<T>.async(scheduler: BaseSchedulerProvider): Observable<T> =
        this.subscribeOn(scheduler.io()).observeOn(scheduler.ui())

/**
 * Use SchedulerProvider configuration for Flowable
 */
fun <T> Flowable<T>.async(scheduler: BaseSchedulerProvider): Flowable<T> =
        this.subscribeOn(scheduler.io()).observeOn(scheduler.ui())

fun <T> Single<T>.loading(liveData: MutableLiveData<Boolean>) =
        doOnSubscribe { liveData.postValue(true) }.doAfterTerminate { liveData.postValue(false) }

fun Completable.loading(liveData: MutableLiveData<Boolean>) =
        doOnSubscribe { liveData.postValue(true) }.doAfterTerminate { liveData.postValue(false) }