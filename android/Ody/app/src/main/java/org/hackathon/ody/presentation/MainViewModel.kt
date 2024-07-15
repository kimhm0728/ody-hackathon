package org.hackathon.ody.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val startingPointLatitude: MutableLiveData<String> = MutableLiveData()
    private val startingPointLongitude: MutableLiveData<String> = MutableLiveData()

    private val destinationLatitude: MutableLiveData<String> = MutableLiveData()
    private val destinationLongitude: MutableLiveData<String> = MutableLiveData()

    fun receiveStartingPoint(startingPointLatitude: String, startingPointLongitude: String) {
        this.startingPointLatitude.value = startingPointLatitude
        this.startingPointLongitude.value = startingPointLongitude
    }

    fun receiveDestination(destinationLatitude: String, destinationLongitude: String) {
        this.destinationLatitude.value = destinationLatitude
        this.destinationLongitude.value = destinationLongitude
    }
}
