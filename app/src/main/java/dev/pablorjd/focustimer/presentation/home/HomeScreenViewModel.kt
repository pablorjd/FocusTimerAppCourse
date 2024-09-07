package dev.pablorjd.focustimer.presentation.home

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_HOUR_IN_MIN
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_MIN_IN_MILLIS
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_MIN_IN_SEC
import dev.pablorjd.focustimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
import dev.pablorjd.focustimer.domain.model.Resource
import dev.pablorjd.focustimer.domain.model.TimerSessionModel
import dev.pablorjd.focustimer.domain.model.TimerTypeEnum
import dev.pablorjd.focustimer.domain.usecase.GetTimerSessionByDateUseCase
import dev.pablorjd.focustimer.domain.usecase.SaveTimerSessionUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.reflect.Constructor
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getTimerSessionByDateUseCase: GetTimerSessionByDateUseCase,
    private val saveTimerSessionUseCase: SaveTimerSessionUseCase
) : ViewModel() {

    private lateinit var timer: CountDownTimer
    private var isTimerActive: Boolean = false
    private val _timerValue = mutableStateOf(TimerTypeEnum.FOCUS.timeToMillis())
    val timerValueState = _timerValue

    private val _timerTypeState = mutableStateOf(TimerTypeEnum.FOCUS)
    val timerTypeState = _timerTypeState

    private val _roundsState = mutableStateOf(0)
    val roundsState = _roundsState

    private val _todayTimeState = mutableStateOf<Long>(0)
    val todayTimeState = _todayTimeState

    var _sessionTimerValue: Long = 0


    fun onStartTimer() {
        viewModelScope.launch {
            timer = object : CountDownTimer(
                _timerValue.value,
                ONE_SEC_IN_MILLIS
            ) {
                override fun onTick(millisUntilFinished: Long) {
                    _timerValue.value = millisUntilFinished
                    _todayTimeState.value += ONE_SEC_IN_MILLIS
                    _sessionTimerValue += ONE_SEC_IN_MILLIS
                }

                override fun onFinish() {
                    onCancelTimer()
                }
            }
            timer.start().also {
                if (!isTimerActive) _roundsState.value += 1
                _sessionTimerValue = 0
                isTimerActive = true
            }
        }
    }

    fun onCancelTimer(reset: Boolean = false) {
        try {
            if (::timer.isInitialized) {
                saveTimerSession()
                timer.cancel()
                _roundsState.value = 0
                isTimerActive = false
                _todayTimeState.value = 0
            }
        } catch (error: UninitializedPropertyAccessException) {
            Log.i("PABLOERRO", error.toString())
        }
        if (!isTimerActive || reset) {
            _timerValue.value = _timerTypeState.value.timeToMillis()
        }
    }

    private fun onResetTime() {
        if (isTimerActive) {
            onCancelTimer()
            onStartTimer()
        }
    }

    fun onUpdateTime(timerTypeEnum: TimerTypeEnum) {
        _timerTypeState.value = timerTypeEnum
        onCancelTimer(true)
    }

    fun onIncreaseTime() {
        _timerValue.value += ONE_MIN_IN_MILLIS
        onResetTime()
    }

    fun onDecreaseTime() {
        _timerValue.value -= ONE_MIN_IN_MILLIS
        onResetTime()
        if (_timerValue.value < 0) {
            onCancelTimer()
        }
    }

    fun getTimerSessionByDate() {
        getTimerSessionByDateUseCase(date = getCurrentDate()).onEach { result ->
            if (result is Resource.Success) {
                _roundsState.value = result.date?.round ?: 0
                _todayTimeState.value = result.date?.value ?: 0
            }
        }.launchIn(viewModelScope)
    }

    private fun saveTimerSession(){
        val session = TimerSessionModel(
            date = getCurrentDate(),
            value = _sessionTimerValue
        )
        saveTimerSessionUseCase(timerSessionModel = session).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _sessionTimerValue = 0
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MMMM-yyyy")
        return formatter.format(currentDate)
    }

    @SuppressLint("DefaultLocale")
    fun millisToMinutes(value: Long): String {
        val totalSeconds = value / ONE_SEC_IN_MILLIS
        val minutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()
        val seconds = (totalSeconds % ONE_MIN_IN_SEC).toInt()
        return String.format("%02d:%02d", minutes, seconds)
    }

    @SuppressLint("DefaultLocale")
    fun millisToHours(value: Long): String {
        val totalSeconds = value / ONE_SEC_IN_MILLIS
        val seconds = (totalSeconds % ONE_MIN_IN_SEC)
        val totalMinutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()
        val hours = (totalMinutes / ONE_HOUR_IN_MIN)
        val minutes = (totalMinutes % ONE_HOUR_IN_MIN)
        return if (totalMinutes <= ONE_HOUR_IN_MIN) {
            String.format("%02dm %02ds", minutes, seconds)
        } else {
            String.format("%02dh %02dm", hours, minutes)
        }
    }

}