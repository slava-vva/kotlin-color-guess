package com.example.colorguess

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityForecastApiBinding
import org.json.JSONArray
import org.json.JSONTokener
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ForecastApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityForecastApiBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        fetchTemperatureData().start()

    }

    private fun fetchTemperatureData(): Thread {
        return Thread {
            val url = URL("http://10.0.2.2:8083/WeatherForecast")

            val connection  = url.openConnection() as HttpURLConnection
            if(connection?.responseCode == 200)
            {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val jsonArray = JSONTokener(inputStreamReader.readText()).nextValue() as JSONArray
                updateUI(jsonArray)
                inputStreamReader.close()
                inputSystem.close()
            }
            else
            {
                binding.lastUpdated.text = "Failed Connection"
            }
        }
    }
    private fun updateUI(jsonArray: JSONArray?) {
        runOnUiThread {
            kotlin.run {
                if (jsonArray != null) {
                    val date = jsonArray.getJSONObject(0).getString("date")
                    binding.lastUpdated.text = date
                    Log.i("Date: ", date)
                    val temperatureC = jsonArray.getJSONObject(0).getString("temperatureC")
                    binding.temperatureC.text = temperatureC
                    Log.i("Temperature Celcius: ", temperatureC)
                    val temperatureF = jsonArray.getJSONObject(0).getString("temperatureF")
                    binding.temperatureF.text = temperatureF
                    Log.i("Temperature Fahremiun: ", temperatureF)
                    val summary = jsonArray.getJSONObject(0).getString("summary")
                    binding.summary.text = summary
                    Log.i("Employee Age: ", summary)
                }
            }
        }
    }
}