package org.hackathon.ody.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val meetingName = MutableLiveData<String>()
    val meetingDate = MutableLiveData<String>()
    val meetingTime = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
}