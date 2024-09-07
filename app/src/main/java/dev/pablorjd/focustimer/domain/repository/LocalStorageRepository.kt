package dev.pablorjd.focustimer.domain.repository

import dev.pablorjd.focustimer.domain.model.TimerSessionModel

interface LocalStorageRepository {
    suspend fun saveTimerSession(timerSessionModel: TimerSessionModel):Boolean
    suspend fun getTimerSessionByDate(date: String): TimerSessionModel
}