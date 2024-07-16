package org.hackathon.ody.presentation.address

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import org.hackathon.ody.domain.GeoCoordinate

class AddressSearchFragmentFactory(
    private val addressReceive: (GeoCoordinate) -> Unit
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            AddressSearchFragment::class.java.name -> AddressSearchFragment(addressReceive)
            else -> super.instantiate(classLoader, className)
        }
    }
}
