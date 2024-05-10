package com.manikbora.multiscreenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class CountryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val countryName = intent.getStringExtra("countryName") ?: "Country not found"

        fetchCountryData(countryName)
    }

    private fun fetchCountryData(countryName: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://restcountries.com/v3.1/name/$countryName")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val json = JSONArray(jsonString)
                    val country = json.getJSONObject(0)
                    val capital = country.getJSONArray("capital").getString(0)
                    val population = country.getString("population")
                    val area = country.getString("area")
                    val region = country.getString("region")
                    val subRegion = country.getString("subregion")

                    runOnUiThread {
                        findViewById<TextView>(R.id.textViewCountryName).text = countryName
                        findViewById<TextView>(R.id.textViewCapital).text = HtmlCompat.fromHtml("<b>Capital:</b> $capital", HtmlCompat.FROM_HTML_MODE_LEGACY)
                        findViewById<TextView>(R.id.textViewPopulation).text = HtmlCompat.fromHtml("<b>Population:</b> $population", HtmlCompat.FROM_HTML_MODE_LEGACY)
                        findViewById<TextView>(R.id.textViewArea).text = HtmlCompat.fromHtml("<b>Area:</b> $area", HtmlCompat.FROM_HTML_MODE_LEGACY)
                        findViewById<TextView>(R.id.textViewRegion).text = HtmlCompat.fromHtml("<b>Region:</b> $region", HtmlCompat.FROM_HTML_MODE_LEGACY)
                        findViewById<TextView>(R.id.textViewSubRegion).text = HtmlCompat.fromHtml("<b>SubRegion:</b> $subRegion", HtmlCompat.FROM_HTML_MODE_LEGACY)
                        // Set other TextViews similarly
                    }
                }
            }
        })
    }
}