package com.github.vitorfg8.popcorn.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.vitorfg8.popcorn.R
import com.github.vitorfg8.popcorn.utils.MediaType

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    companion object {
        fun getIntent(id: Int, mediaType: MediaType) {

        }
    }
}