package com.example.colorguess

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.colorguess.databinding.ActivityDialBinding
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Toast

class DialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()

        binding.btnDial.setOnClickListener {
            val textNumber = binding.editPhoneNumber.text.toString()
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$textNumber")
            startActivity(intent)
        }

        binding.btnSendSMS.setOnClickListener {
            try {
                val textNumber = binding.editPhoneNumber.text.toString()
                val textSMS = binding.editSMS.text.toString()
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(textNumber, null, textSMS, null, null)
                Toast.makeText(this, "SMS sent!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }

        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                101
            )
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.SEND_SMS),
                101
            )
        }
    }

}

