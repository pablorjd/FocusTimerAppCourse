package dev.pablorjd.focustimer.domain.model

import dev.pablorjd.focustimer.data.local.entity.TimerSessionEntity

// modelo que interacttua con la capa de presentacion de la aplicacion
data class TimerSessionModel(
    var date: String,
    var value: Long,
    var round: Int? = 0
)

fun TimerSessionModel.toTimerSessionEntity(): TimerSessionEntity {
    return TimerSessionEntity(
        date = this.date,
        value = this.value
    )
}
