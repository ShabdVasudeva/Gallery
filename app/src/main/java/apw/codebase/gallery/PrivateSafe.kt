package apw.codebase.gallery

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.*
import com.google.android.material.elevation.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.provider.MediaStore
import apw.codebase.gallery.securenv.*
import apw.codebase.gallery.databinding.ActivitySafeBinding

class PrivateSafe: AppCompatActivity() {
    
    private var _binding: ActivitySafeBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: PrivateSafeDatabase
    companion object {
        private const val REQUEST_CODE_AUTHENTICATION = 1001
        private const val REQUEST_IMAGE = 1002
        private const val WINDOW_FLAG = WindowManager.LayoutParams.FLAG_SECURE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySafeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authenticateWithKeyguard()
        window.setFlags(
            WINDOW_FLAG,
            WINDOW_FLAG
        )
        database = PrivateSafeDatabase(this@PrivateSafe)
        setSupportActionBar(binding.toolbar)
        val images = database.getAllImagePaths()
        val adapter = ImageAdapter(images){image ->
            
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this@PrivateSafe, 4)
        binding.recyclerView.adapter = adapter
        window.navigationBarColor = SurfaceColors.SURFACE_0.getColor(this@PrivateSafe)
        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
        binding.add.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun authenticateWithKeyguard() {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (keyguardManager.isKeyguardSecure) {
            val intent = keyguardManager.createConfirmDeviceCredentialIntent(
                "Authentication Required",
                "Please authenticate to access private safe"
            )
            startActivityForResult(intent, REQUEST_CODE_AUTHENTICATION)
        } else {
            Toast.makeText(this, "No lock screen security set up", Toast.LENGTH_LONG).show()
            finish()
        }
    }
  
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE){
            val imageUri = data?.data
            if(imageUri != null){
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                val path: String? = ImageUtils.saveBitmapToFile(this@PrivateSafe, bitmap)
                database.insertImagePath(path!!)
                loadImages()
            }
        }
        if (requestCode == REQUEST_CODE_AUTHENTICATION) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Authentication Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    
    private fun loadImages(){
        val images = database.getAllImagePaths()
        val adapter = ImageAdapter(images){image ->
            
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this@PrivateSafe, 4)
        binding.recyclerView.adapter = adapter
    }
    
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}