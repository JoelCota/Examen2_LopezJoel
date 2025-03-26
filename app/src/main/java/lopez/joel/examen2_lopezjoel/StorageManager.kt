package lopez.joel.examen2_lopezjoel

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lopez.joel.examen2_lopezjoel.model.Cancion
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStreamReader

class StorageManager(private val context: Context) {

    private val fileName = "canciones.json"

    fun saveCanciones(canciones: List<Cancion>) {
        val gson = Gson()
        val json = gson.toJson(canciones)

        try {
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(json.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadCanciones(): List<Cancion> {
        val gson = Gson()
        val file = File(context.filesDir, fileName)

        return if (file.exists()) {
            try {
                val reader = InputStreamReader(context.openFileInput(fileName))
                val cancionesType = object : TypeToken<List<Cancion>>() {}.type
                val canciones: List<Cancion> = gson.fromJson(reader, cancionesType)
                reader.close()
                canciones
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                emptyList()
            }
        } else {
            emptyList()
        }
    }
    fun removeCancion(cancion: Cancion) {
        val canciones = loadCanciones().toMutableList()

        canciones.remove(cancion)

        saveCanciones(canciones)
    }

    fun addCancion(cancion: Cancion) {
        val canciones = loadCanciones().toMutableList()

        canciones.add(cancion)

        saveCanciones(canciones)
    }


}
