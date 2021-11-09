package com.delnortedevs.pappcontrol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delnortedevs.pappcontrol.databinding.ActivityMainBinding
import com.delnortedevs.pappcontrol.databinding.ItemPatientBinding

class AdapterPatient (var patients: List<Patient>) : RecyclerView.Adapter<AdapterPatient.ViewHolder>() {

    class ViewHolder(val binding: ItemPatientBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPatientBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.textViewPatientName.text = patients[position].name
        holder.binding.ageNumberPatient.text = patients[position].age.toString()

    }

    override fun getItemCount(): Int {
        return patients.size
    }
}