package com.example.wallpaper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaper.R
import com.example.wallpaper.interface_clicklistener.ItemClickListener
import com.example.wallpaper.model.Photo
import kotlinx.android.synthetic.main.item_layout.view.*

class ImageAdapter(private val list: List<Photo>,val itemClickListener: ItemClickListener):RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
     val pic=list[position]
        holder.setData(pic,itemClickListener)
    }

    override fun getItemCount(): Int {
      return list.size
    }
    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
     fun setData(photo: Photo,itemClickListener: ItemClickListener){
         itemView.apply {
                 Glide.with(image)
                     .load(photo.src.medium)
                     .into(image)
         }
         itemView.image.setOnClickListener {
           itemClickListener.ClickListener(photo,adapterPosition)
         }
     }
    }
}