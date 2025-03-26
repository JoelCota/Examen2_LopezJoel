package lopez.joel.examen2_lopezjoel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import lopez.joel.examen2_lopezjoel.model.Cancion


class AddCancionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cancion)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        val etNombre: EditText = findViewById(R.id.etNombre)
        val etArtista: EditText = findViewById(R.id.etArtista)
        val etDuracion: EditText = findViewById(R.id.etDuracion)
        val etAlbum: EditText = findViewById(R.id.etAlbum)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)

        btnBack.setOnClickListener {
            finish()
        }

        btnGuardar.setOnClickListener {
            val nombreCancion = etNombre.text.toString()
            val artista = etArtista.text.toString()
            val duracion = etDuracion.text.toString()
            val album = etAlbum.text.toString()

            if (nombreCancion.isNotEmpty() && artista.isNotEmpty() && duracion.isNotEmpty()) {
                val nuevaCancion = Cancion(nombreCancion, artista, duracion, album)

                val resultIntent = Intent()
                resultIntent.putExtra("cancion", nuevaCancion)

                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
