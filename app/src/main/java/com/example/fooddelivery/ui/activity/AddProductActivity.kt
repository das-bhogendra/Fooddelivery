package com.example.fooddelivery.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelivery.R
import com.example.fooddelivery.viewmodel.ProductViewModel
import com.example.fooddelivery.databinding.ActivityAddProductBinding
import com.example.fooddelivery.model.ProductModel
import com.example.fooddelivery.repository.ProductRepositoryImpl
import com.example.fooddelivery.utils.ImageUtils
import com.example.fooddelivery.utils.LoadingUtils
import com.squareup.picasso.Picasso

class AddProductActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddProductBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var loadingUtils: LoadingUtils
    lateinit var imageUtils: ImageUtils



    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageUtils = ImageUtils(this)

        loadingUtils=LoadingUtils(this)
        var repo=ProductRepositoryImpl()
        productViewModel=ProductViewModel(repo)
        imageUtils.registerActivity { url ->
            url.let { it ->
                imageUri = it
                Picasso.get().load(it).into(binding.imageBrowse)
            }
        }
        binding.imageBrowse.setOnClickListener {
            imageUtils.launchGallery(this)
        }
        binding.Productbtn.setOnClickListener {
            uploadImage()

        }
        binding.Productbtn.setOnClickListener{
            val name=binding.editProductName.text.toString()
            val price=binding.editProductprice.text.toString().toInt()
            val desc=binding.editProductDescription.text.toString()
            var model=ProductModel("",name,desc,price)
            productViewModel.addFoodItem(model){
                success, message->
                if(success){
                    Toast.makeText(this@AddProductActivity,message,Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@AddProductActivity,message,Toast.LENGTH_LONG).show()

                }
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun uploadImage() {
        loadingUtils.show()
        imageUri?.let { uri ->
            productViewModel.uploadImage(this, uri) { imageUrl ->
                Log.d("checpoirs", imageUrl.toString())
                if (imageUrl != null) {
                    addProduct(imageUrl)
                } else {
                    Log.e("Upload Error", "Failed to upload image to Cloudinary")
                }
            }
        }
    }

    private fun addProduct(url: String) {
        var productName = binding.editProductName.text.toString()
        var productPrice = binding.editProductprice.text.toString().toInt()
        var productDesc = binding.editProductDescription.text.toString()

        var model = ProductModel(
            "",
            productName,
            productDesc, productPrice, url
        )

        productViewModel.addFoodItem(model) { success, message ->
            if (success) {
                Toast.makeText(
                    this@AddProductActivity,
                    message, Toast.LENGTH_LONG
                ).show()
                finish()
                loadingUtils.dismiss()
            } else {
                Toast.makeText(
                    this@AddProductActivity,
                    message, Toast.LENGTH_LONG
                ).show()
                loadingUtils.dismiss()
            }
        }
    }
}