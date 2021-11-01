package learn.bymyself.sholatreminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataTanggal: ArrayList<String>,
              private val dataSubuh: ArrayList<String>,
              private val dataDzuhur: ArrayList<String>,
              private val dataAshar: ArrayList<String>,
              private val dataMagrib: ArrayList<String>,
              private val dataIsya: ArrayList<String>) :
    RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal : TextView
        val subuh: TextView
        val dzuhur: TextView
        val ashar: TextView
        val magrib: TextView
        val isya: TextView

        init {
            tanggal = view.findViewById(R.id.tanggal)
            subuh = view.findViewById(R.id.subuh)
            dzuhur = view.findViewById(R.id.dzuhur)
            ashar = view.findViewById(R.id.ashar)
            magrib = view.findViewById(R.id.magrib)
            isya = view.findViewById(R.id.isya)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_model, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tanggal.text = dataTanggal[position]
        viewHolder.subuh.text = dataSubuh[position]
        viewHolder.dzuhur.text = dataDzuhur[position]
        viewHolder.ashar.text = dataAshar[position]
        viewHolder.magrib.text = dataMagrib[position]
        viewHolder.isya.text = dataIsya[position]
    }

    override fun getItemCount() = dataTanggal.size

}