package com.example.patientsapi.presentation.features.patients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patientsapi.presentation.databinding.RowPatientBinding

class PatientsAdapter(private val patients: List<com.example.patientsapi.domain.model.patients.PatientRemoteModel>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    var indexLastSelected = -1

    inner class PatientsViewHolder(private val binding: RowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: com.example.patientsapi.domain.model.patients.PatientRemoteModel, position: Int) {
            binding.model = model
            binding.cardView.setOnClickListener {
                if (position != indexLastSelected) {

                    // if not default
                    // notify last item
                    if (indexLastSelected != -1) {
                        patients[indexLastSelected].selected = false
                        notifyItemChanged(indexLastSelected)
                    }

                    // notify new item
                    indexLastSelected = position
                    patients[position].selected = true
                    notifyItemChanged(adapterPosition)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val binding = RowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientsViewHolder(binding)
    }

    override fun getItemCount() = patients.size

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val model = patients[position]
        holder.bind(model, position)
    }

}