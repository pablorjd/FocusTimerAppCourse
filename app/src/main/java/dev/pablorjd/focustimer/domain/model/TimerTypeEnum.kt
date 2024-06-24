package dev.pablorjd.focustimer.domain.model

import dev.pablorjd.focustimer.core.Constants.Companion.FOCUS_TIME
import dev.pablorjd.focustimer.core.Constants.Companion.LONG_BREAK_TIME
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_MIN_IN_SEC
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
import dev.pablorjd.focustimer.core.Constants.Companion.SHORT_BREAK_TIME

enum class TimerTypeEnum(val title:String, private val time:Long) {

    FOCUS(title = "Focus Time",FOCUS_TIME),
    SHORT_BREAK(title = "Short Break", SHORT_BREAK_TIME),
    LONG_BREAK(title = "Long Break", LONG_BREAK_TIME);

    fun timeToMillis():Long {
        return time * ONE_MIN_IN_SEC * ONE_SEC_IN_MILLIS
    }

}