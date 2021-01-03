package com.example.abc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun setUpRecyclerView(list: List<Person>) {
        val profileAdapter = ProfileAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = profileAdapter
    }

    override fun onResume() {
        super.onResume()
        userApi.getUsers(4955).enqueue(ResponseHandler())
    }

    inner class ResponseHandler : Callback<PersonResponce> {
        override fun onFailure(call: Call<PersonResponce>, t: Throwable) {
            Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_LONG).show()
        }

        override fun onResponse(call: Call<PersonResponce>, response: Response<PersonResponce>) {
            val profileResponce = response.body()
            if (profileResponce != null) {
                setUpRecyclerView(profileResponce.results)
            }
        }
    }
}
