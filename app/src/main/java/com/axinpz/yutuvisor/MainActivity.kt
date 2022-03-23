package com.axinpz.yutuvisor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubePlayer
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlaySingle = findViewById<Button>(R.id.btn_PlaySingle)
        val btnMenu = findViewById<Button>(R.id.btn_subMenu)

        btnPlaySingle.setOnClickListener(this)
        btnMenu.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id){
            //A que actividad se llamara apra ponerla sobre la lista de actividades
            //De la clase que tienes, pasame la compilada en kotlin pasalo a java
            R.id.btn_PlaySingle -> Intent(this, yutuPlayerActivity::class.java)
            R.id.btn_subMenu -> Intent(this, MenuActivity::class.java)
            else -> throw IllegalArgumentException("Invalid Action")
        }
        startActivity(intent)
    }
}