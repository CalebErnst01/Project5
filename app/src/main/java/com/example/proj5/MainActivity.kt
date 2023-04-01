package com.example.proj5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.random.Random

class MainActivity : AppCompatActivity()
{
    var baseValue = 50.0
    lateinit var con1: TextView
    lateinit var con2: TextView
    lateinit var con3: TextView
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        con1 = findViewById(R.id.conValueOne)
        con2 = findViewById(R.id.conValueTwo)
        con3 = findViewById(R.id.conValueThree)
        btn  = findViewById(R.id.button)

        btn.setOnClickListener {
            convertCurrency().start()
        }
    }

    public fun convertCurrency(): Thread
    {
        return Thread {
            val url = URL("https://open.er-api.com/v6/latest/usd")
            val connect = url.openConnection() as HttpsURLConnection

            //if connection successful
            if(connect.responseCode == 200)
            {
                val input = connect.inputStream
                val inReader = InputStreamReader(input, "UTF-8")
                val request = Gson().fromJson(inReader, Request::class.java)

                refreshApp(request)

                inReader.close()
                input.close()
            }
            else
            {
                con1.text = "Connection Failed!"
                con2.text = "Connection Failed!"
                con3.text = "Connection Failed!"
            }
        }
    }

    public fun refreshApp(request: Request)
    {
        runOnUiThread {
            kotlin.run {
                val rand1 = (0..10).random()

                when (rand1) {
                    0 -> con1.text = "AUD: " + convert(request.AUD)
                    1 -> con1.text = "AED: " + convert(request.AED)
                    2 -> con1.text = "AFN: " + convert(request.AFN)
                    3 -> con1.text = "ALL: " + convert(request.ALL)
                    4 -> con1.text = "CAD: " + convert(request.CAD)
                    5 -> con1.text = "EUR: " + convert(request.EUR)
                    6 -> con1.text = "GBP: " + convert(request.GBP)
                    7 -> con1.text = "NZD: " + convert(request.NZD)
                    8 -> con1.text = "SEK: " + convert(request.SEK)
                    9 -> con1.text = "TOP: " + convert(request.TOP)
                    10 -> con1.text = "VES: " + convert(request.VES)
                }

                var rand2 = (0..10).random()

                while(rand1 == rand2)
                {
                    rand2 = (0..10).random()
                }

                when (rand2) {
                    0 -> con2.text = "AUD: " + convert(request.AUD)
                    1 -> con2.text = "AED: " + convert(request.AED)
                    2 -> con2.text = "AFN: " + convert(request.AFN)
                    3 -> con2.text = "ALL: " + convert(request.ALL)
                    4 -> con2.text = "CAD: " + convert(request.CAD)
                    5 -> con2.text = "EUR: " + convert(request.EUR)
                    6 -> con2.text = "GBP: " + convert(request.GBP)
                    7 -> con2.text = "NZD: " + convert(request.NZD)
                    8 -> con2.text = "SEK: " + convert(request.SEK)
                    9 -> con2.text = "TOP: " + convert(request.TOP)
                    10 -> con2.text = "VES: " + convert(request.VES)
                }

                var rand3 = (0..10).random()

                while(rand1 == rand3 || rand2 == rand3)
                {
                    rand3 = (0..10).random()
                }

                when (rand3) {
                    0 -> con3.text = "AUD: " + convert(request.AUD)
                    1 -> con3.text = "AED: " + convert(request.AED)
                    2 -> con3.text = "AFN: " + convert(request.AFN)
                    3 -> con3.text = "ALL: " + convert(request.ALL)
                    4 -> con3.text = "CAD: " + convert(request.CAD)
                    5 -> con3.text = "EUR: " + convert(request.EUR)
                    6 -> con3.text = "GBP: " + convert(request.GBP)
                    7 -> con3.text = "NZD: " + convert(request.NZD)
                    8 -> con3.text = "SEK: " + convert(request.SEK)
                    9 -> con3.text = "TOP: " + convert(request.TOP)
                    10 -> con3.text = "VES: " + convert(request.VES)
                }
            }
        }
    }

    public fun convert(rate: Double): String
    {
        return (baseValue * rate).toString()
    }
}