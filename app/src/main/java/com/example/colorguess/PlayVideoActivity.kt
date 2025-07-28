package com.example.colorguess

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityPlayVideoBinding

class PlayVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPlayVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonChooseVideo.setOnClickListener {
            val videoView = binding.videoView
            //Creating MediaController
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            //specify the location of media file
            val uri = Uri.parse("android.resource://${packageName}/${R.raw.video1}")
            //Setting MediaController and URI, then starting the videoView
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()
        }

    }
}