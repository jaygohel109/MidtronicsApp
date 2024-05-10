package com.manikbora.multiscreenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)

        // Set up the OnClickListener for the "Profile Page" button
        btnSecondActivity.setOnClickListener {
            // Intent to start SecondActivity (assuming you have SecondActivity defined)
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }


        val layoutButtonContainer = findViewById<LinearLayout>(R.id.layoutButtonContainer)
        val countries = resources.getStringArray(R.array.countries_array)

        countries.forEach { country ->
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            button.text = country
            button.contentDescription = "Button for $country"
            button.setOnClickListener {
                val intent = Intent(this, CountryDetailActivity::class.java)
                intent.putExtra("countryName", country)
                startActivity(intent)
            }
            layoutButtonContainer.addView(button)
        }
    }
}