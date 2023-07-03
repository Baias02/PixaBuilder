package com.example.pixabuilder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabuilder.databinding.ItemImageBinding

class PixaAdapter : Adapter<PixaAdapter.PixaViewHolder>() {
    var list = arrayListOf<ImageModel>()

    class PixaViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.image.load(model.largeImageURL)
        }
    }

    fun addImage(image: ArrayList<ImageModel>) {
        list.addAll(image)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}