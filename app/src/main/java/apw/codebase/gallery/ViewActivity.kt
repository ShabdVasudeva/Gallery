package apw.codebase.gallery;

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import android.view.*
import androidx.viewpager2.widget.ViewPager2
import android.util.Log
import android.graphics.*
import android.provider.Settings
import android.Manifest
import android.net.*
import android.content.*
import apw.codebase.gallery.databinding.*

class ViewActivity: AppCompatActivity() {
    private var _binding: ActivityViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageList: List<MediaFile>
    private var startPosition: Int = 0
    private var isUIVisible = true
    
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        _binding = ActivityViewBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
        imageList = intent.getParcelableArrayListExtra("mediaList") ?: emptyList()
        startPosition = intent.getIntExtra("position", 0)
        window.navigationBarColor = Color.parseColor("#000000")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        window.statusBarColor = Color.parseColor("#000000")
        val currentPosition = binding.viewPager.currentItem
        getSupportActionBar()!!.title = imageList[currentPosition].name
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(pos: Int){
                super.onPageSelected(pos)
                getSupportActionBar()!!.title = imageList[pos].name
            }
        })
        val adapter = ImagePagerAdapter(this@ViewActivity, imageList){toggleUIVisibility()}
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(startPosition, false)
        binding.share.setOnClickListener {
            val currentPosition = binding.viewPager.currentItem
            val currentUri = imageList[currentPosition].uri
            shareImage(this, currentUri)
        }
        binding.edit.setOnClickListener{
            val pos = binding.viewPager.currentItem
            editImage(this, imageList[pos].uri)
        }
        binding.delete.setOnClickListener{
            val pos = binding.viewPager.currentItem
            deleteImageFromUri(this, imageList[pos].uri)
        }
    }

    fun deleteImageFromUri(context: Context, imageUri: Uri): Boolean {
        return try {
            val contentResolver: ContentResolver = context.contentResolver
            val rowsDeleted = contentResolver.delete(imageUri, null, null)
            rowsDeleted > 0
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Please Allow All files access permission first", Toast.LENGTH_SHORT).show()
            false
        }
    }
    
    fun editImage(context: Context, imageUri: Uri) {
        try {
            var intent =  Intent(Intent.ACTION_EDIT)
            intent.setDataAndType(imageUri, "image/*")
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(intent, null))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "No editor found!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun toggleUIVisibility() {
        if (isUIVisible) {
            binding.toolbar.visibility = View.GONE
            binding.bottomBar.visibility = View.GONE
        } else {
            binding.toolbar.visibility = View.VISIBLE
            binding.bottomBar.visibility = View.VISIBLE
        }
        isUIVisible = !isUIVisible
    }
    
    fun shareImage(context: Context, imageUri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }
    
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}