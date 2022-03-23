package com.axinpz.yutuvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer
import java.lang.IllegalArgumentException

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSingle = findViewById<Button>(R.id.btn_PlayVideo)
        val btnList = findViewById<Button>(R.id.btn_Playlist)

        btnSingle.setOnClickListener(this)
        btnList.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when(view.id) {
            R.id.btn_PlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID_KEY)
            R.id.btn_Playlist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), PLAYLIST_ID_KEY)
            else -> throw IllegalArgumentException("Invalid button")
        }
        startActivity(intent)
    }
}
