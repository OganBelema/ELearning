package com.oganbelema.elearning.di.module

import android.content.Context
import com.oganbelema.elearning.di.qualifier.ApplicationContext
import com.oganbelema.elearning.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }
}