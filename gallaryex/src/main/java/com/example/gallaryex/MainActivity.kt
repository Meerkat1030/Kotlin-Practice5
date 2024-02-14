package com.example.gallaryex

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gallaryex.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    // 적절한 비율로 이미지 크기 줄이기
    private fun calculateInSmpleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        }catch (e: Exception){
            e.printStackTrace()
        }
        val (height:Int, width: Int) = options.run { outHeight to outWidth}
        var inSampleSize = 1
        // inSampleSize 비율 계산
        if(height > reqHeight || width > reqWidth){
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while(halfHeight / inSampleSize >= reqHeight &&
            halfWidth / inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val requestGalleryLanuncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            try{
                val calRatio = calculateInSmpleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelOffset(R.dimen.imgSize),
                    resources.getDimensionPixelOffset(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio
                // 이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?. let{
//                    Log.d("test", "bitmap null")
                    binding.galleryResult.setImageBitmap(bitmap)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.button.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLanuncher.launch(intent)
        }
    }
}