package com.example.colorguess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityEmailBinding
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class EmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSendEmail.setOnClickListener {

            val txtTo = binding.editTo.text.toString()
            val txtSubj = binding.editSubject.text.toString()
            val txtBody = binding.editBody.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, txtTo)
                putExtra(Intent.EXTRA_SUBJECT, txtSubj)
                putExtra(Intent.EXTRA_TEXT, txtBody)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else{
                Toast.makeText(this@EmailActivity, "Required App is not installed", Toast.LENGTH_SHORT).show()
            }

        }

    }
}