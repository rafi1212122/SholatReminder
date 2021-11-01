package learn.bymyself.sholatreminder.Network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//class Config {
//    fun retrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.pray.zone/v2/times/")
//            .build()
//    }
//
//    fun service() = retrofit().create(jadwalModel::class.java)
//}
//
//interface jadwalModel {
//    @GET("this_month.json?longitude=106.816666&latitude=-6.200000&elevation=8")
//    fun getJadwalModel(): Call<JadwalModel>
//}


class Config {

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.pray.zone/v2/times/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(ModelWaktu::class.java)

}

interface ModelWaktu{
    @GET("month.json?longitude=106.816666&latitude=-6.200000&elevation=8&month=2021-10")
    fun getModelWaktu() : Call<JadwalModel>
}