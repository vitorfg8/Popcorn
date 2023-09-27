package com.github.vitorfg8.popcorn.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.vitorfg8.popcorn.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.topAppBar))
    }
}