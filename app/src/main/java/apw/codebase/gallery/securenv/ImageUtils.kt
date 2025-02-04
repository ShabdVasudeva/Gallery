package apw.codebase.gallery.securenv;

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import apw.codebase.gallery.securenv.*

object ImageUtils {

    fun shareImage(context: Context, imagePath: String) {
        val file = File(imagePath)
        if (!file.exists()) {
            return
        }
        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }

    fun saveBitmapToFile(context: Context, bitmap: Bitmap): String? {
        val fileName = "apwpvtspc_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        return try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    
    fun deletePath(filePath: String): Boolean{
        val file = File(filePath)
        return if(
            file.exists()
        ){file.delete()}
        else{
            false
        }
    }
}