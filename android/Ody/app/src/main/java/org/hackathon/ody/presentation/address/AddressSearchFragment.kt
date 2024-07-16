package org.hackathon.ody.presentation.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebViewAssetLoader
import org.hackathon.ody.R
import org.hackathon.ody.data.remote.location.KakaoLocationRepository
import org.hackathon.ody.domain.GeoCoordinate

class AddressSearchFragment(private val addressReceive: (GeoCoordinate) -> Unit) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_address_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.findViewById<WebView>(R.id.wv_address_search)) {
            settings.javaScriptEnabled = true
            val addressSearchInterface = AddressSearchInterface(onReceive = { address ->
                val geoCoordinate = KakaoLocationRepository.getGeoCoordinate(address)
                addressReceive(geoCoordinate)
                this.visibility = View.GONE
            })
            addJavascriptInterface(addressSearchInterface, JS_BRIDGE)

            val assetLoader = WebViewAssetLoader.Builder()
                .addPathHandler(
                    "/${PATH}/",
                    WebViewAssetLoader.AssetsPathHandler(requireContext()),
                )
                .setDomain(DOMAIN)
                .build()

            webViewClient = LocalContentWebViewClient(assetLoader)
            loadUrl("https://${DOMAIN}/${PATH}/address.html")
        }
    }

    companion object {
        private const val DOMAIN = "org.hackathon.ody"
        private const val PATH = "assets"
        private const val JS_BRIDGE = "address_search"
    }
}
