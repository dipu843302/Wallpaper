package com.example.wallpaper.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaper.adapter.ImageAdapter
import com.example.wallpaper.R
import com.example.wallpaper.api.API
import com.example.wallpaper.api.Network
import com.example.wallpaper.interface_clicklistener.ItemClickListener
import com.example.wallpaper.model.Photo
import com.example.wallpaper.viewmodel.ImageViewModel
import com.example.wallpaper.viewmodel.Image_Repo
import com.example.wallpaper.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ItemClickListener {
    lateinit var imageViewModel: ImageViewModel
private var imageList= mutableListOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api =Network.getInstance().create(API::class.java)
        val imageRepo=Image_Repo(api)
        imageViewModel=ViewModelProvider(this, ViewModelFactory(imageRepo)).get(ImageViewModel::class.java)
        imageViewModel.images.observe(this, Observer {
            it.let {
                imageList.clear()
                imageList.addAll(it.photos)
                setRecyclerView()

            }
        })
    }
    private fun setRecyclerView() {
        val adapterClass = ImageAdapter(imageList,this)
        recyclerView.adapter=adapterClass
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
    }

    override fun ClickListener(photo: Photo, position: Int) {
        val intent=Intent(this,MainActivity2::class.java)
        intent.putExtra("image",photo.src.medium)
        startActivity(intent)
    }
}