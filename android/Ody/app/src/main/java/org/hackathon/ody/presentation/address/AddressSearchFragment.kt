package org.hackathon.ody.presentation.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.webkit.WebViewAssetLoader
import org.hackathon.ody.R

class AddressSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_address_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.findViewById<WebView>(R.id.wv_address_search)) {
            settings.javaScriptEnabled = true

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
        private const val DOMAIN = "address.finder.net"
        private const val PATH = "assets"
    }
}
