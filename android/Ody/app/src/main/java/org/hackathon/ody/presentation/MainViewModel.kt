package org.hackathon.ody.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.hackathon.ody.domain.GeoCoordinate

class MainViewModel : ViewModel() {
    val meetingName = MutableLiveData<String>()
    val meetingDate = MutableLiveData<String>()
    val meetingTime = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()

    private val startingPointGeoCoordinate: MutableLiveData<GeoCoordinate> = MutableLiveData()
    private val destinationGeoCoordinate: MutableLiveData<GeoCoordinate> = MutableLiveData()

    fun receiveStartingPoint(geoCoordinate: GeoCoordinate) {
        startingPointGeoCoordinate.value = geoCoordinate
        Log.d("MainViewModel", "시작 위치 x: ${geoCoordinate.latitude}, y: ${geoCoordinate.longitude}")
    }

    fun receiveDestination(geoCoordinate: GeoCoordinate) {
        destinationGeoCoordinate.value = geoCoordinate
        Log.d("MainViewModel", "도착 위치 x: ${geoCoordinate.latitude}, y: ${geoCoordinate.longitude}")
    }
}
