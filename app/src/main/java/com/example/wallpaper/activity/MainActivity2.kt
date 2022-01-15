package com.example.wallpaper.activity
import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wallpaper.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL


class MainActivity2 : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val url=intent.getStringExtra("image")

        val urlImage =URL(url)

        Glide.with(this).load(url).into(ivImage)

        button.setOnClickListener( View.OnClickListener {
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.IO) {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }

           Toast.makeText(this,"Wallpaper set",Toast.LENGTH_SHORT).show()
        })

    }
    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }

    }

}