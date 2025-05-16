package com.example.exchange

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exchange.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationBarView
import androidx.fragment.app.commit


class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)

    }

    private fun onBooksClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, (HomeFragment()))
        }
        return true
    }

    private fun onAddClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, (AddFragment()))
        }
        return true
    }

    private fun onProfileClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, (ProfileFragment()))
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.books -> {
            onBooksClicked()
            true
        }
        R.id.add -> {
            onAddClicked()
            true
        }
        R.id.profile -> {
            onProfileClicked()
            true
        }
        else -> false

    }
}