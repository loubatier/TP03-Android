package com.batierlou.tp03_batier_lou.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.batierlou.tp03_batier_lou.NavigationListener
import com.batierlou.tp03_batier_lou.R
import com.batierlou.tp03_batier_lou.databinding.ActivityMainBinding
import com.batierlou.tp03_batier_lou.di.DI
import com.batierlou.tp03_batier_lou.ui.fragments.ListNeighborsFragment

class MainActivity : AppCompatActivity(), NavigationListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showFragment(ListNeighborsFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun updateTitle(title: Int) {
        binding.toolbar.setTitle(title)
    }
}
