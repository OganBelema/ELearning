package com.oganbelema.elearning.di.module

import android.content.Context
import androidx.room.Room
import com.oganbelema.database.AppDatabase
import com.oganbelema.elearning.di.qualifier.ApplicationContext
import com.oganbelema.elearning.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Belema Ogan on 1/18/21.
 */
@Module(includes = [ApplicationContextModule::class])
class DatabaseModule {
    @Provides
    @ApplicationScope
    internal fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
            .build()
    }
}