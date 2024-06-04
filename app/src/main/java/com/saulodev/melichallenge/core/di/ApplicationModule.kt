package com.saulodev.melichallenge.core.di

import android.content.Context
import com.saulodev.melichallenge.core.application.CoreApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    /**
     * Provides an instance of CoreApplication.
     *
     * @param app The application context.
     * @return The application instance cast to CoreApplication.
     */
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): CoreApplication {
        return app as CoreApplication
    }

}