package dev.pablorjd.focustimer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pablorjd.focustimer.data.local.AppDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java, name = "focus_timer_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTimerSessionDao(db: AppDataBase) = db.timerSessionDao()
}