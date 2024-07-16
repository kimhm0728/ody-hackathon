package org.hackathon.ody.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MeetingInfoAdapter(fragmentActivity: FragmentActivity, private val numberOfPage: Int = 6) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = numberOfPage

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MeetingNameFragment()
            1 -> MeetingDateFragment()
            2 -> MeetingTimeFragment()
            3 -> MeetingNicknameFragment()
            4 -> MeetingStartingPointFragment()
            else -> MeetingDestinationFragment()
        }
    }
}
