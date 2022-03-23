package com.axinpz.yutuvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID_KEY = "JhDIILjlhBQ"
const val PLAYLIST_ID_KEY = "PL4U4df-wZ-3auKgF7lVFgjaMvUJeypO9z"

//Prepara la UI
class yutuPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    val TAG = "yutuPlayerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_yutu_player)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        //Aqui primero se crea y luego se setea, es igual que las dos lineas de arriba
        val layout = layoutInflater.inflate(R.layout.activity_yutu_player, null) as ConstraintLayout
        setContentView(layout)

        //Establecer widgets programando
//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 100)
//        button1.text = "Button added"
//        layout.addView(button1)

        //Widget que viene por defecto de Android Studio. Es como lo del
        //boton de arriba
        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT )
        layout.addView(playerView)

        //Para inicializar el widget
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess( provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean ) {
        Log.d(TAG, "onInitializationSuccess")
        Toast.makeText(this, "Initialized Successfully", Toast.LENGTH_LONG).show()

        player?.setPlaybackEventListener(playBackListener)
        player?.setPlayerStateChangeListener(changeStateListener)

        if(!wasRestored) {
            player?.cueVideo(YOUTUBE_VIDEO_ID_KEY)
        }
    }

    override fun onInitializationFailure( provider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult? ) {
        Log.d(TAG, "onInitializationFailure")
        val REQUEST_CODE = 0
        //Aqui entra si es por culpa del usuario
        if(youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()

        //Si no es culpa del usuario
        } else {
            //Mensaje que aparecen pequeños y discretos
            Toast.makeText(this, "Error starting player", Toast.LENGTH_LONG).show()
        }
    }

    //Eventos de tipo Evento
    private val playBackListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            //el @ hace que se refiera a la actividad completa, proque this solo se refiere al playBackListener. Se hace referencia al Activity compelto
            Toast.makeText(this@yutuPlayerActivity, "Playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@yutuPlayerActivity, "Paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }

    //Eventos de cambio de Estado
    private val changeStateListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@yutuPlayerActivity, "Add", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@yutuPlayerActivity, "Finished", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }

    }

}