package com.example.dhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.*
import java.io.IOException

class Dashboard : AppCompatActivity() {
    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //run("https://api.openweathermap.org/data/2.5/forecast?q=purwakarta,ID&appid=2ce659b9c25fc6fe3a07de4ca71d1dac")

        NetworkConfig().getService()
            .getWeathers("purwakarta,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac")
            .enqueue(object : retrofit2.Callback<ResultWeather> {

                override fun onResponse(
                    call: retrofit2.Call<ResultWeather>,
                    response: retrofit2.Response<ResultWeather>
                ) {
                    var item = response.body()
                    Log.d("response", response.body().toString())


                }

                override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                    Log.d("error response service", t.message.toString())

                    Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG)
                        .show()


                }
            })
    }
     fun run(url: String){
         val request = Request.Builder()
             .url(url)
             .build()

         client.newCall(request).enqueue(object : Callback {
             override fun onFailure(call: Call, e: IOException) {}
             override fun onResponse(call: Call, response: Response) = println(response.body()?.string())





         })

    }
}