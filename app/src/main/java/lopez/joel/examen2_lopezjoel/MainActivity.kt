package lopez.joel.examen2_lopezjoel

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lopez.joel.examen2_lopezjoel.model.Cancion

class MainActivity : AppCompatActivity() {

    private lateinit var gridViewCanciones: GridView
    private lateinit var btnAgregar: Button
    private lateinit var listaCanciones: ArrayList<Cancion>
    private lateinit var cancionAdapter: CancionAdapter
    private lateinit var storageManager: StorageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storageManager = StorageManager(this)

        listaCanciones = ArrayList(storageManager.loadCanciones())

        if (listaCanciones.isEmpty()) {
            listaCanciones = getCancionesPorDefecto()
            storageManager.saveCanciones(listaCanciones)
        }

        gridViewCanciones = findViewById(R.id.gridViewCanciones)
        btnAgregar = findViewById(R.id.btnAgregar)

        cancionAdapter = CancionAdapter(this, listaCanciones)
        gridViewCanciones.adapter = cancionAdapter

        gridViewCanciones.setOnItemClickListener { _, _, position, _ ->
            val cancion = listaCanciones[position]
            val intent = Intent(this, DetalleCancionActivity::class.java)
            intent.putExtra("nombreCancion", cancion.nombre)
            intent.putExtra("artista", cancion.artista)
            intent.putExtra("duracion", cancion.duracion)
            intent.putExtra("album", cancion.album)
            intent.putExtra("position", position)
            startActivityForResult(intent, REQUEST_CODE_DETALLE)
        }

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AddCancionActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_AGREGAR)
        }
    }

    fun getCancionesPorDefecto(): ArrayList<Cancion> {
        return arrayListOf(
            Cancion("Shape of You", "Ed Sheeran", "3:53", "Divide"),
            Cancion("Blinding Lights", "The Weeknd", "3:20", "After Hours"),
            Cancion("Levitating", "Dua Lipa", "3:24", "Future Nostalgia"),
            Cancion("Tití Me Preguntó", "Bad Bunny", "4:24", "Un Verano Sin Ti"),
            Cancion("Peaches", "Justin Bieber", "3:18", "Justice"),
            Cancion("Save Your Tears", "The Weeknd", "3:35", "After Hours"),
            Cancion("Watermelon Sugar", "Harry Styles", "2:54", "Fine Line"),
            Cancion("Industry Baby", "Lil Nas X", "3:32", "Montero"),
            Cancion("Kiss Me More", "Doja Cat", "3:28", "Planet Her"),
            Cancion("Stay", "The Kid LAROI & Justin Bieber", "2:21", "F*ck Love"),
            Cancion("Good 4 U", "Olivia Rodrigo", "2:58", "SOUR"),
            Cancion("Montero (Call Me By Your Name)", "Lil Nas X", "2:18", "Montero"),
            Cancion("Bad Habits", "Ed Sheeran", "4:03", "Equals"),
            Cancion("Beggin'", "Måneskin", "3:30", "Chosen"),
            Cancion("Shivers", "Ed Sheeran", "3:27", "Equals"),
            Cancion("Deja Vu", "Olivia Rodrigo", "4:04", "SOUR"),
            Cancion("Levitating", "Dua Lipa feat. DaBaby", "3:24", "Future Nostalgia"),
            Cancion("Take My Breath", "The Weeknd", "3:38", "Dawn FM"),
            Cancion("Cold Heart (Pnau Remix)", "Elton John & Dua Lipa", "3:22", "The Lockdown Sessions"),
            Cancion("Save Your Tears (Remix)", "The Weeknd & Ariana Grande", "3:45", "Dawn FM")
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_DETALLE && resultCode == RESULT_OK) {
            val position = data?.getIntExtra("position", -1) ?: -1
            if (position != -1) {
                // Eliminar la canción seleccionada
                val cancionAEliminar = listaCanciones[position]
                storageManager.removeCancion(cancionAEliminar)
                listaCanciones.removeAt(position)
                cancionAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Canción eliminada", Toast.LENGTH_SHORT).show()
            }
        }

        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK) {
            val cancion = data?.getSerializableExtra("cancion") as Cancion
            listaCanciones.add(cancion)
            storageManager.saveCanciones(listaCanciones)
            cancionAdapter.notifyDataSetChanged()
        }
    }


    class CancionAdapter(context: Context, canciones: List<Cancion>) :
        ArrayAdapter<Cancion>(context, 0, canciones) {

        private val mContext: Context = context

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_cancion, parent, false)
            }

            val cancion = getItem(position)

            val tvNombreCancion: TextView = view!!.findViewById(R.id.tvNombreCancion)
            val tvArtista: TextView = view.findViewById(R.id.tvArtista)

            tvNombreCancion.text = cancion?.nombre
            tvArtista.text = cancion?.artista

            return view
        }
    }

    companion object {
        const val REQUEST_CODE_DETALLE = 1
        const val REQUEST_CODE_AGREGAR = 2
    }

}

