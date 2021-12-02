package com.delnortedevs.pappcontrol

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delnortedevs.pappcontrol.databinding.ActivityMainBinding
import com.delnortedevs.pappcontrol.databinding.ItemPatientBinding

class AdapterPatient (val context: Context, var patients: List<Patient>, private val onItemclicked: (Patient)-> Unit) : RecyclerView.Adapter<AdapterPatient.ViewHolder>() {

    class ViewHolder(val binding: ItemPatientBinding, onItemclicked: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener{
                onItemclicked(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPatientBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding){
            onItemclicked(patients[it])
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.textViewPatientName.text = patients[position].name
        holder.binding.ageNumberPatient.text = patients[position].age.toString()

    }

    override fun getItemCount(): Int {
        return patients.size
    }
}