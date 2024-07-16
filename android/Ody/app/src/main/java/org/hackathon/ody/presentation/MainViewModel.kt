package org.hackathon.ody.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.hackathon.ody.data.remote.RetrofitClient
import org.hackathon.ody.data.remote.reqeust.MeetingInfoRequest
import org.hackathon.ody.data.remote.service.MeetingService
import org.hackathon.ody.domain.GeoCoordinate
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainViewModel(val service: MeetingService = RetrofitClient.getRetrofit().create(MeetingService::class.java)) : ViewModel() {
    val meetingName = MutableLiveData<String>()
    val meetingDate = MutableLiveData<String>()
    val meetingTime = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()

    private val startingPointGeoCoordinate: MutableLiveData<GeoCoordinate> = MutableLiveData()
    private val destinationGeoCoordinate: MutableLiveData<GeoCoordinate> = MutableLiveData()

    val event: MutableLiveData<Unit> = MutableLiveData()

    fun receiveStartingPoint(geoCoordinate: GeoCoordinate) {
        startingPointGeoCoordinate.value = geoCoordinate
        Log.d("MainViewModel", "시작 위치 x: ${geoCoordinate.latitude}, y: ${geoCoordinate.longitude}")
    }

    fun receiveDestination(geoCoordinate: GeoCoordinate) {
        destinationGeoCoordinate.value = geoCoordinate
        Log.d("MainViewModel", "도착 위치 x: ${geoCoordinate.latitude}, y: ${geoCoordinate.longitude}")
    }

    fun register() {
        viewModelScope.launch {
            service.postMeetingRoom(
                MeetingInfoRequest(
                    name = meetingName.value!!,
                    date = "2024-07-03",
                    time = "14:30",
                    targetAddress = destinationGeoCoordinate.value!!.address,
                    targetLatitude = destinationGeoCoordinate.value!!.latitude,
                    targetLongitude = destinationGeoCoordinate.value!!.longitude,
                    nickname = nickname.value!!,
                    originAddress = startingPointGeoCoordinate.value!!.address,
                    originLatitude = startingPointGeoCoordinate.value!!.latitude,
                    originLongitude = startingPointGeoCoordinate.value!!.longitude,
                )
            )
            event.value = Unit
        }
    }

    private fun stringToLocalDate(dateString: String, pattern: String = "yyyy-MM-dd"): LocalDate {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalDate.parse(dateString, formatter)
    }

    private fun stringToLocalTime(timeString: String, pattern: String = "HH:mm"): LocalTime {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return LocalTime.parse(timeString, formatter)
    }
}
