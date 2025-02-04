package apw.codebase.gallery.securenv;

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.graphics.*
import androidx.recyclerview.widget.*
import com.google.android.material.elevation.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import apw.codebase.gallery.*
import android.provider.MediaStore
import android.net.Uri
import apw.codebase.gallery.securenv.*
import apw.codebase.gallery.databinding.LayoutPrivateImageViewerBinding

class PrivateImageViewer: AppCompatActivity(){
    
    private var _binding: LayoutPrivateImageViewerBinding? = null
    private val binding get() = _binding!!
    
    companion object {
        private const val WINDOW_FLAGS = WindowManager.LayoutParams.FLAG_SECURE
        private lateinit var imageList: List<ImageItem>
        private var startPosition: Int = 0
        private var isUIVisible = true
        private lateinit var database: PrivateSafeDatabase
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        _binding = LayoutPrivateImageViewerBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        window.setFlags(
            WINDOW_FLAGS,
            WINDOW_FLAGS
        )
        database = PrivateSafeDatabase(this)
        window.navigationBarColor = Color.parseColor("#000000")
        window.statusBarColor = Color.parseColor("#000000")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        imageList = intent.getParcelableArrayListExtra("mediaList") ?: emptyList()
        startPosition = intent.getIntExtra("position", 0)
        val adapter = PrivatePagerAdapter(this, imageList){toggleUIVisibility()}
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(startPosition, false)
        binding.share.setOnClickListener {
            val pos = binding.viewPager.currentItem
            ImageUtils.shareImage(this, imageList[pos].imagePath)
        }
        binding.delete.setOnClickListener{
            val pos = binding.viewPager.currentItem
            database.deleteImagePath(imageList[pos].imagePath)
            ImageUtils.deletePath(imageList[pos].imagePath)
            Toast.makeText(this, "Deleted successfuly changes may occur on next open", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun toggleUIVisibility() {
        if (isUIVisible) {
            binding.toolbar.visibility = View.GONE
            binding.bottomBar2.visibility = View.GONE
        } else {
            binding.toolbar.visibility = View.VISIBLE
            binding.bottomBar2.visibility = View.VISIBLE
        }
        isUIVisible = !isUIVisible
    }
    
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}
