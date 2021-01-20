package com.oganbelema.elearning.di.component

import android.content.Context
import com.oganbelema.database.AppDatabase
import com.oganbelema.elearning.ui.MainActivity
import com.oganbelema.elearning.MyApplication
import com.oganbelema.elearning.di.module.DatabaseModule
import com.oganbelema.elearning.di.module.NetworkModule
import com.oganbelema.elearning.di.qualifier.ApplicationContext
import com.oganbelema.elearning.di.scope.ApplicationScope
import com.oganbelema.network.Api
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent{

    fun getApiInterface(): Api
    fun getDatabase(): AppDatabase
    fun inject(activity: MainActivity)
    fun injectApplication(app: MyApplication)
    @ApplicationContext
    fun getContext(): Context

}