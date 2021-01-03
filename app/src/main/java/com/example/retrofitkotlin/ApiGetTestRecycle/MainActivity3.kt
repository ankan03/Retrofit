package com.example.retrofitkotlin.ApiGetTestRecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.R
import kotlinx.android.synthetic.main.activity_main3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllUsers().enqueue(object :Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                showData(response.body())
                d("Ankan","OnResponse: ${response.body()!![0]?.phone}")
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                d("Ankan","OnFailure")
            }
        })

//        val user = mutableListOf<User>()
//        for (i in 0..100){
//            user.add(User("Ankan","Mukherjee","1828049@kiit.ac.in","123456789"))
//        }


    }

    private fun showData(user: List<User>?) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity3)
            adapter = UsersAdapter(user!!)
        }
    }

}