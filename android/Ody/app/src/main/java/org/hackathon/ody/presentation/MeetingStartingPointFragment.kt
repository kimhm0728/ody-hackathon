package org.hackathon.ody.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.hackathon.ody.R
import org.hackathon.ody.presentation.address.AddressSearchFragment

class MeetingStartingPointFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_meeting_starting_point, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAddressSearchView()
    }

    private fun showAddressSearchView() {
        childFragmentManager.beginTransaction()
            .add(R.id.fragment_address_search, AddressSearchFragment())
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}
