package com.github.vitorfg8.popcorn.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.home.trends.ui.TrendsFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val trendsFragment = TrendsFragment.newInstance("", "") //TODO
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, trendsFragment)
            .commit()
    }
}