package com.example.retrofitkotlin.RetrofitGetRecycler

import retrofit2.Call
import retrofit2.http.GET

interface ApiCountryService {
    @GET("/constants/country_code")
    fun fetchAllCountries(): Call<List<CountryItem>>
}