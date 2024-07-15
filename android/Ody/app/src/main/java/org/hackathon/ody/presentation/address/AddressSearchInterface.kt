package org.hackathon.ody.presentation.address

import android.os.Handler
import android.os.Looper

class AddressSearchInterface(private val onReceive: (String, String) -> Unit) {
    @android.webkit.JavascriptInterface
    fun receive(address: String, zipCode: String) {
        Handler(Looper.getMainLooper()).post {
            onReceive(address, zipCode)
        }
    }
}
