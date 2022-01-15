package com.example.wallpaper.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaper.adapter.ImageAdapter
import com.example.wallpaper.R
import com.example.wallpaper.api.API
import com.example.wallpaper.api.Network
import com.example.wallpaper.interface_clicklistener.ItemClickListener
import com.example.wallpaper.model.Photo
import com.example.wallpaper.model.ResponseDTO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),ItemClickListener {
private var list= listOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api =Network.getInstance().create(API::class.java)
        api.getImage("563492ad6f91700001000001294f1f4e2a564bdaa903fa3403cdae53","people").enqueue(object : Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                list = response.body()!!.photos!!
                setRecyclerView()
            }
            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                Toast.makeText(applicationContext,"No data",Toast.LENGTH_SHORT).show()
            }
        }
        )
    }
    private fun setRecyclerView() {
        val adapterClass = ImageAdapter(list,this)
        recyclerView.adapter=adapterClass
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
    }

    override fun ClickListener(photo: Photo, position: Int) {
        val intent=Intent(this,MainActivity2::class.java)
        intent.putExtra("image",photo.src.original)
        startActivity(intent)
    }
}