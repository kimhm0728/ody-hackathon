package org.hackathon.ody.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val startingPoint: MutableLiveData<String> = MutableLiveData()

    fun receiveStartingPoint(startingPoint: String) {
        this.startingPoint.value = startingPoint
    }
}
