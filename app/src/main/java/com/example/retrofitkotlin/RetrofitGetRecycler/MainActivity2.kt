package com.example.retrofitkotlin.RetrofitGetRecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.R
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofitkotlin.RetrofitGetRecycler.CountryAdapter as CountryAdapter

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-15-207-110-35.ap-south-1.compute.amazonaws.com:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiCountryService::class.java)
        api.fetchAllCountries().enqueue(object : Callback<List<CountryItem>> {
            override fun onResponse(call: Call<List<CountryItem>>, response: Response<List<CountryItem>>) {
                showData(response.body())
                Log.d("Ankan", "OnResponse: ${response.body()!![0]?.country_code}")
            }

            override fun onFailure(call: Call<List<CountryItem>>, t: Throwable) {
                Log.d("Ankan", "OnFailure")
            }
        })
            }

    private fun showData(countries: List<CountryItem>?) {
        recyclerView_spinner.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = CountryAdapter(countries!!)
        }
    }
}