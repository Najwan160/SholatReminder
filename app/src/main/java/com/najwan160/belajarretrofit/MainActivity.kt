package com.najwan160.belajarretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.najwan160.belajarretrofit.data.DataBogor
import com.najwan160.belajarretrofit.data.DataJakarta
import com.najwan160.belajarretrofit.data.DataSurabaya
import com.najwan160.belajarretrofit.network.Config
import com.najwan160.belajarretrofit.network.JadwalModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    val dataWaktu = arrayListOf<String>()
    val dataSubuh = arrayListOf<String>()
    val dataDzuhur = arrayListOf<String>()
    val dataAshar = arrayListOf<String>()
    val dataMagrib = arrayListOf<String>()
    val dataIsya = arrayListOf<String>()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        val etCity : EditText = findViewById(R.id.etCity)
        val btnSubmit : ImageButton = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener{
            if (etCity.text.toString().lowercase(Locale.getDefault()) == "jakarta"){
                Clear()
                showData(DataJakarta().long, DataJakarta().lat)
                Toast.makeText(this, "Jakarta", Toast.LENGTH_LONG).show()
            } else if (etCity.text.toString().lowercase(Locale.getDefault()) == "surabaya"){
                Clear()
                showData(DataSurabaya().long, DataSurabaya().lat)
                Toast.makeText(this, "Surabya", Toast.LENGTH_LONG).show()
            }
            else if (etCity.text.toString().lowercase(Locale.getDefault()) == "bogor"){
                Clear()
                showData(DataBogor().long, DataBogor().lat)
                Toast.makeText(this, "bogor", Toast.LENGTH_LONG).show()
            }else {
                val warning = "Masukkan dengan benar"
                Toast.makeText(this, warning, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun showData(long: String, lat: String, ev: String = "8", mo: String = "2021-10") {

        Config().getService().getModelWaktu(long, lat, ev, mo)
            .enqueue(object : Callback<JadwalModel> {

                override fun onResponse(
                    call: Call<JadwalModel>,
                    response: Response<JadwalModel>
                ) {
                    val panggil1 = response.body()
                    val panggil2 = panggil1?.results?.datetime

                    for (list in panggil2!!.indices) {
                        val waktuSholat = panggil2[list]?.times
                        val tanggal = panggil2[list]?.date

                        dataWaktu.add(tanggal?.gregorian.toString())
                        dataSubuh.add(waktuSholat?.fajr.toString())
                        dataDzuhur.add(waktuSholat?.dhuhr.toString())
                        dataAshar.add(waktuSholat?.asr.toString())
                        dataMagrib.add(waktuSholat?.maghrib.toString())
                        dataIsya.add(waktuSholat?.isha.toString())

                        recyclerView.adapter = ModelAdapter(
                            dataWaktu,
                            dataSubuh,
                            dataDzuhur,
                            dataAshar,
                            dataMagrib,
                            dataIsya
                        )
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recyclerView.setHasFixedSize(true)
                    }
                }
                override fun onFailure(call: Call<JadwalModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun Clear() {
        dataSubuh.clear()
        dataDzuhur.clear()
        dataAshar.clear()
        dataMagrib.clear()
        dataIsya.clear()
        dataWaktu.clear()
    }

}
