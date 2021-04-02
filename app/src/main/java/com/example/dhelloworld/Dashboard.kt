package com.example.dhelloworld

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.item_dasboard.*
import okhttp3.*
import java.io.IOException

@Suppress("UNCHECKED_CAST")
class Dashboard : AppCompatActivity() {
    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        NetworkConfig().getService().getWeathers("Cirebon,ID", "2ce659b9c25fc6fe3a07de4ca71d1dac").enqueue(object : retrofit2.Callback<ResultWeather> {

                override fun onResponse(call: retrofit2.Call<ResultWeather>, response: retrofit2.Response<ResultWeather>) {
                    var item = response.body()
                    Log.d("response", response.body().toString())
                    kota.text = item?.city?.name
                    tgl.text = item?.list?.get(0)?.dt?.let { Util.getDayName(it) }
                    tempMax.text = item?.list?.get(0)?.main?.temp?.let { Util.setFormatTemperature(it) }
                    tempMin.text = item?.list?.get(0)?.main?.tempMin?.let { Util.setFormatTemperature(it) } + " - " + item?.list?.get(0)?.main?.tempMax?.let { Util.setFormatTemperature(it) }

                    text_desc.text = item?.list?.get(0)?.weather?.get(0)?.description.toString()
                    item?.list?.get(0)?.weather?.get(0)?.id?.let {
                        Util.getArtResourceForWeatherCondition(
                            it
                        )
                    }?.let {
                        image_desc.setImageResource(it) }

                    val list = item?.list
                    val itemAdp = ItemAdapter(list as List<ListItem>)


                    RecyclerviewItem.apply {
                        layoutManager = LinearLayoutManager(this@Dashboard)
                        adapter = itemAdp

                    }


                }

            override fun onFailure(call: retrofit2.Call<ResultWeather>, t: Throwable) {
                Log.d("error response service", t.message.toString())

                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    fun run(url: String){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })

    }
}



