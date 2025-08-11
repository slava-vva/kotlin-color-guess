package com.example.colorguess

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityFeaturesBinding
import androidx.core.net.toUri
import java.util.Calendar

class FeaturesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeaturesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeaturesBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPlayVideo.setOnClickListener {
            startActivity(Intent(this, PlayVideoActivity ::class.java))
        }

        binding.btnGoogleCalendar.setOnClickListener {
//            title = "Kotlin Google Calendar"
//
//            val calendarEvent: Calendar = Calendar.getInstance()
//            val intent = Intent(Intent.ACTION_EDIT)
//            intent.type = "vnd.android.cursor.item/event"
//            intent.putExtra("beginTime", calendarEvent.timeInMillis)
//            intent.putExtra("allDay", true)
//            intent.putExtra("rule", "FREQ=YEARLY")
//            intent.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
//            intent.putExtra("title", "Calendar Event")
//            startActivity(intent)

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_CALENDAR)
            startActivity(intent)

        }

        binding.imageButtonPick.setOnClickListener {
            startActivity(Intent(this, PickImagesActivity ::class.java))
        }

        binding.btnGoogleMaps.setOnClickListener {
            startActivity(Intent(this, GoogleMapsActivity ::class.java))
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnBrowser.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, "https://spacenews.com/".toUri())
            startActivity(intent)
        }

        binding.btnDial.setOnClickListener {
            startActivity(Intent(this, DialActivity::class.java))

        }

        binding.btnSMS.setOnClickListener {
            startActivity(Intent(this, DialActivity::class.java))

        }

        binding.btnSendEmail.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))

        }

    }
}