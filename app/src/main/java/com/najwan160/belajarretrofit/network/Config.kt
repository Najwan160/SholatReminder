package com.najwan160.belajarretrofit.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


//class Config {
//
//    fun retrofit(): Retrofit{
//        return Retrofit.Builder()
//            .baseUrl("https://api.pray.zone/v2/times/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun service() = retrofit().create(JadwalModelInterface::class.java)
//
//
//}
//
//interface JadwalModelInterface {
//    @GET("month.json?longitude=39.81666564941406&latitude=21.416667938232425&elevation=333&month=2021-10")
//    fun listJadwalModel(): Call<JadwalModel>
//}

class Config {

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.pray.zone/v2/times/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(JadwalModelInterface::class.java)

}

//interface JadwalModelInterface{
//    @GET("month.json")
//    fun getModelWaktu(@Query("longitude")long:String,
//                      @Query("latitude")lat:String,
//                      @Query("elevation")el:String,
//                      @Query("month")mo:String
//                      ) : Call<JadwalModel>
//}

interface JadwalModelInterface{
    @GET("month.json")
    fun getModelWaktu(@Query("city")city:String,
                      @Query("month")mo:String
    ) : Call<JadwalModel1>
}






