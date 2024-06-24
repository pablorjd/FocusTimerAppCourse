package dev.pablorjd.focustimer.presentation.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel

class HomeScreenViewModel:ViewModel() {

    private lateinit var timer:CountDownTimer
    private var isTimerActive:Boolean = false


}