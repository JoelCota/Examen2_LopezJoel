package lopez.joel.examen2_lopezjoel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CancionPlayActivity : AppCompatActivity() {
    private lateinit var tvSongName: TextView
    private lateinit var tvSongInfo: TextView
    private lateinit var btnPlayPause: Button
    private lateinit var btnStop: Button

    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion_play)

        // Inicializamos las vistas
        tvSongName = findViewById(R.id.tvSongName)
        tvSongInfo = findViewById(R.id.tvSongInfo)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnStop = findViewById(R.id.btnStop)

        // Recibimos el nombre de la canción y el artista
        val songName = intent.getStringExtra("songName") ?: "Unknown Song"
        val artist = intent.getStringExtra("artist") ?: "Unknown Artist"

        // Establecemos el nombre de la canción y el artista en la UI
        tvSongName.text = songName
        tvSongInfo.text = "Artist: $artist"

        // Funcionalidad del botón Play/Pause
        btnPlayPause.setOnClickListener {
            if (isPlaying) {
                pauseSong()
            } else {
                playSong()
            }
        }

        // Funcionalidad del botón Stop
        btnStop.setOnClickListener {
            stopSong()
        }
    }

    // Función para simular la reproducción de la canción
    private fun playSong() {
        isPlaying = true
        btnPlayPause.text = "Pause"
    }

    // Función para simular la pausa de la canción
    private fun pauseSong() {
        isPlaying = false
        btnPlayPause.text = "Play"
    }

    // Función para detener la canción y regresar a la actividad anterior
    private fun stopSong() {
        finish() // Regresa a la actividad anterior (DetalleCancionActivity)
    }
}