package de.chillercode.deepdive.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsDetailViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Detail"
    }
    val text: LiveData<String> = _text
}