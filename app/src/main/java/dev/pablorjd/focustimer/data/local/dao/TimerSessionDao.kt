package dev.pablorjd.focustimer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pablorjd.focustimer.data.local.entity.TimerSessionEntity

@Dao
interface TimerSessionDao {

    @Query("SELECT * FROM timer_session WHERE date like '%'||:date||'%'")
    suspend fun getTimerSessionByDate(date: String): MutableList<TimerSessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //remplaza el valor si tiene o encuentra conflicto
    suspend fun insertTimerSession(timerSessionEntity: TimerSessionEntity): Long
}