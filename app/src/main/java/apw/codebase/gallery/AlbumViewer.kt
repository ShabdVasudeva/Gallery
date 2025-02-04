package apw.codebase.gallery;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apw.codebase.gallery.databinding.*
import androidx.recyclerview.widget.*
import android.util.Log

class AlbumViewer: AppCompatActivity(){
    
    private var _binding: LayoutAlbumViewerBinding? = null
    private val binding get() = _binding!!
    private var mediaFiles: List<MediaFile> = listOf()
    private lateinit var adapter: MediaAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        _binding = LayoutAlbumViewerBinding.inflate(getLayoutInflater())
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val folderName = intent.getStringExtra("folderName")
        mediaFiles = intent.getParcelableArrayListExtra("mediaList") ?: emptyList()
        getSupportActionBar()!!.title = folderName
        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
        Log.e("AlbumError",mediaFiles.size.toString())
        adapter = MediaAdapter(mediaFiles)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        binding.recyclerView.adapter = adapter
    }
    
    private fun fetchMediaFilesFromFolder(folderName: String?): List<MediaFile> {
        val allMediaFiles = FetchAll(this).fetchMediaFiles()
        return allMediaFiles
    }
    
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}
