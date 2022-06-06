package com.example.kotlinsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsample.R
import com.example.kotlinsample.databinding.ItemDocumentBinding
import com.example.kotlinsample.model.Document

class DocumentAdapter(private val listDocument: List<Document>?) :
    RecyclerView.Adapter<DocumentAdapter.ViewHolder>() {

    private lateinit var binding: ItemDocumentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listDocument!=null){
            val doc = listDocument.get(position)
            holder.bind(doc)
        }

    }

    override fun getItemCount(): Int {
        if(listDocument!=null){
            return listDocument.size
        }
        return 0
    }

    inner class ViewHolder(private val binding: ItemDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(document: Document) {
            binding.document = document
        }
    }

}