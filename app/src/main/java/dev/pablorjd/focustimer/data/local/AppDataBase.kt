package dev.pablorjd.focustimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.pablorjd.focustimer.data.local.dao.TimerSessionDao
import dev.pablorjd.focustimer.data.local.entity.TimerSessionEntity

@Database(entities = [TimerSessionEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun timerSessionDao(): TimerSessionDao
}