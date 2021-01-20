package com.oganbelema.elearning

import android.app.Application
import com.oganbelema.elearning.di.component.ApplicationComponent
import com.oganbelema.elearning.di.component.DaggerApplicationComponent
import com.oganbelema.elearning.di.module.ApplicationContextModule
import timber.log.Timber

/**
 * Created by Belema Ogan on 1/15/21.
 */
class MyApplication: Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            return this.instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        instance = this
        applicationComponent = initDagger(this)

    }

    private fun initDagger(app: MyApplication): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationContextModule(ApplicationContextModule(app))
            .build()
}