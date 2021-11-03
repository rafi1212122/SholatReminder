package learn.bymyself.sholatreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import learn.bymyself.sholatreminder.Network.JadwalModel
import learn.bymyself.sholatreminder.Network.Config
import learn.bymyself.sholatreminder.data.DataJakarta
import learn.bymyself.sholatreminder.data.DataSurabaya
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    val dataTanggal = arrayListOf<String>()
    val dataSubuh = arrayListOf<String>()
    val dataDzuhur = arrayListOf<String>()
    val dataAshar = arrayListOf<String>()
    val dataMagrib = arrayListOf<String>()
    val dataIsya = arrayListOf<String>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_time)

        val editText: EditText = findViewById(R.id.editText)
        val searchBtn : ImageButton = findViewById(R.id.search_btn)

        searchBtn.setOnClickListener{
            if(editText.text.toString().lowercase(Locale.getDefault()) == "jakarta"){
                clear()
                showData(DataJakarta().lon, DataJakarta().lat, DataJakarta().ele, DataJakarta().mo)
                Toast.makeText(this, "Jakarta", Toast.LENGTH_LONG).show()
            } else if (editText.text.toString().lowercase(Locale.getDefault()) == "surabaya"){
                clear()
                showData(DataSurabaya().lon, DataSurabaya().lat, DataSurabaya().ele, DataSurabaya().mo)
                Toast.makeText(this, "Jakarta", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Masukkan dengan benar", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showData(lon:String, lat:String, ele:String, mon:String){
        Config().getService().getModelWaktu(lon, lat, ele, mon).enqueue(object : Callback<JadwalModel> {

            override fun onResponse(call: Call<JadwalModel>, response: Response<JadwalModel>) {
                val panggil1 = response.body()
                val panggil2 = panggil1?.results?.datetime

                for (list in panggil2!!.indices){
                    val waktuSholat = panggil2[list]?.times
                    val tanggal = panggil2[list]?.date

                    dataTanggal.add(tanggal?.gregorian.toString())
                    dataSubuh.add(waktuSholat?.fajr.toString())
                    dataDzuhur.add(waktuSholat?.dhuhr.toString())
                    dataAshar.add(waktuSholat?.asr.toString())
                    dataMagrib.add(waktuSholat?.maghrib.toString())
                    dataIsya.add(waktuSholat?.isha.toString())

                    recyclerView.adapter = Adapter(dataTanggal, dataSubuh, dataDzuhur, dataAshar, dataMagrib, dataIsya)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<JadwalModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT ).show()
            }

        })
    }

    private fun clear(){
        dataSubuh.clear()
        dataDzuhur.clear()
        dataAshar.clear()
        dataMagrib.clear()
        dataIsya.clear()
        dataTanggal.clear()
    }

}