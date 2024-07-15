package org.hackathon.ody.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.hackathon.ody.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagerAdapter = MeetingInfoAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = pagerAdapter
    }
}