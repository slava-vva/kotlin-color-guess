package com.example.colorguess

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ControlsActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox: CheckBox
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controls)

        radioGroup = findViewById(R.id.radioGroup)
        checkBox = findViewById(R.id.checkBox)
        editText = findViewById(R.id.editText)
        recyclerView = findViewById(R.id.recyclerView)
        scrollView = findViewById(R.id.scrollView)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        // RecyclerView setup
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleAdapter((1..20).map { "Item $it" })

        // CheckBox onClick
        checkBox.setOnClickListener {
            Log.d("UI_EVENT", "CheckBox clicked: ${checkBox.isChecked}")
        }

        // RadioButton onClick via XML (android:onClick)
        // See XML for setup

        // EditText focus change
        editText.setOnFocusChangeListener { _, hasFocus ->
            Log.d("UI_EVENT", "EditText focus: $hasFocus")
        }

        // EditText key press
        editText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                Log.d("UI_EVENT", "Key pressed: $keyCode")
            }
            false
        }

        // Touch listener on ScrollView
        scrollView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                Log.d("UI_EVENT", "ScrollView touched")
            }
            false
        }
    }

    fun onRadioButtonClicked(view: View) {
        val radioButton = view as RadioButton
        if (radioButton.isChecked) {
            Log.d("UI_EVENT", "RadioButton clicked: ${radioButton.text}")
        }
    }

    // RecyclerView Adapter
    class SimpleAdapter(private val items: List<String>) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = TextView(parent.context).apply {
                setPadding(20, 20, 20, 20)
                textSize = 16f
            }
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }

        override fun getItemCount() = items.size
    }
}
