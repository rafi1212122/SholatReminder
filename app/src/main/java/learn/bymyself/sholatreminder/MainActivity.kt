package learn.bymyself.sholatreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import learn.bymyself.sholatreminder.Network.Config
import learn.bymyself.sholatreminder.Network.JadwalModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private var dataSubuh = mutableListOf<String>()
    private var dataDzuhur = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvWaktu: RecyclerView = findViewById(R.id.tanggal)
        val rvSubuh: RecyclerView = findViewById(R.id.subuh)
        val rvDzuhur: RecyclerView = findViewById(R.id.dzuhur)
        val rvAshar: RecyclerView = findViewById(R.id.ashar)
        val rvMaghrib: RecyclerView = findViewById(R.id.maghrib)
        val rvIsya: RecyclerView = findViewById(R.id.isya)

        Config().getService().getModelWaktu().enqueue(object : Callback<JadwalModel>{
            override fun onResponse(
                call: Call<JadwalModel>,
                response: Response<JadwalModel>
            ) {
                val hasil = response.body()!!
                val hasil2 = hasil.results?.datetime
                if (hasil2 != null) {
                    for (i in hasil2){
                        dataSubuh.add(i?.times?.fajr.toString())
                        dataDzuhur.add(i?.times?.dhuhr.toString())

                        rvSubuh.adapter = Adapter(dataSubuh)
                        rvDzuhur.adapter = Adapter(dataDzuhur)

                        rvSubuh.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvDzuhur.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(call: Call<JadwalModel>, t: Throwable) {
                val announce = "Masalah koneksi, 403"
                Toast.makeText(this@MainActivity, announce, Toast.LENGTH_LONG).show()
            }

        })

    }
}