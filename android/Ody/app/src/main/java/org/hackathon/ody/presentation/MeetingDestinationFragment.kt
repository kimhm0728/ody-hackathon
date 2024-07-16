package org.hackathon.ody.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import org.hackathon.ody.R
import org.hackathon.ody.databinding.FragmentMeetingDestinationBinding
import org.hackathon.ody.presentation.address.AddressSearchFragment
import org.hackathon.ody.presentation.address.AddressSearchFragmentFactory

class MeetingDestinationFragment : Fragment() {
    private var _binding: FragmentMeetingDestinationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeetingDestinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAddressSearchView()
    }

    private fun showAddressSearchView() {
        parentFragmentManager.fragmentFactory =
            AddressSearchFragmentFactory(addressReceive = { geoCoordinate ->
                viewModel.receiveDestination(geoCoordinate)
                binding.etMeetingDate.setText(geoCoordinate.address)
            })
        val fragment = parentFragmentManager.fragmentFactory.instantiate(
            requireActivity().classLoader,
            AddressSearchFragment::class.java.name
        )
        parentFragmentManager.commit {
            add(R.id.fragment_address_search, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
