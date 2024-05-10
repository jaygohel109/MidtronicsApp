package com.manikbora.multiscreenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val imageViewProfile = findViewById<ImageView>(R.id.imageViewProfile)
        val textViewName = findViewById<TextView>(R.id.textViewName)

        // Here you can set the name dynamically if needed
        textViewName.text = "Jay Gohel"  // Replace with dynamic data as needed
        val textViewBio = findViewById<TextView>(R.id.textViewBio)
        textViewBio.text = "I am Jay Gohel.I am doing my Master's in Computer Science from Illinois Institute od Technology. I have nearly 2 years of work experience as Software Engineer, where i developed skillsets like Python, React.js, JavaScript, MongoDB, GraphQL, JIRA, Git etc."

        val tableEducation = findViewById<TableLayout>(R.id.tableEducation)
        val btnFirstActivity = findViewById<Button>(R.id.btnFirstActivity)
        btnFirstActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}