package com.ninhttd.vozsticker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val imageList: List<Image>):
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       return holder.bind(imageList[position])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var imageView = itemView.findViewById<ImageView>(R.id.image)
        fun bind(image: Image) {
            Picasso.get().load(image.link).into(imageView)
        }
    }

}