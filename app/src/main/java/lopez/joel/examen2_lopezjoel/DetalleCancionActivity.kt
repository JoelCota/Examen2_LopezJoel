package lopez.joel.examen2_lopezjoel

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class DetalleCancionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_cancion)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        val tvNombreCancion: TextView = findViewById(R.id.tvNombreCancion)
        val tvArtista: TextView = findViewById(R.id.tvArtista)
        val tvDuracion: TextView = findViewById(R.id.tvDuracion)
        val tvAlbum: TextView = findViewById(R.id.tvAlbum)
        val btnReproducir: Button = findViewById(R.id.btnReproducir)
        val btnEliminar: TextView = findViewById(R.id.btnEliminar)

        val nombreCancion = intent.getStringExtra("nombreCancion") ?: "Canción Desconocida"
        val artista = intent.getStringExtra("artista") ?: "Artista Desconocido"
        val duracion = intent.getStringExtra("duracion") ?: "Desconocida"
        val position = intent.getIntExtra("position", -1)
        val album = intent.getStringExtra("album")?: "Desconocida"

        tvNombreCancion.text = nombreCancion
        tvArtista.text = artista
        tvDuracion.text = duracion
        tvAlbum.text=album
        btnReproducir.text="Play ${nombreCancion}"
        btnBack.setOnClickListener {
            finish()
        }

        btnReproducir.setOnClickListener {
            val intent = Intent(this, CancionPlayActivity::class.java)
            intent.putExtra("songName", nombreCancion)
            intent.putExtra("artist", artista)
            startActivity(intent)
        }

        btnEliminar.setOnClickListener {
            if (position != -1) {
                val intent = Intent()
                intent.putExtra("position", position)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
