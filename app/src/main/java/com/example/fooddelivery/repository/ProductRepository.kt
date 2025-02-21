package com.example.fooddelivery.repository


import android.content.Context
import android.net.Uri
import com.example.fooddelivery.model.ProductModel

import com.google.android.play.integrity.internal.u

interface ProductRepository {

//    {
    //    "success" : true,
    //    "message: "Product delete successfully"}



    fun addFoodItem(productModel: ProductModel,
                    callback:(Boolean, String)-> Unit)

    fun updateFoodItem(productId : String,data:MutableMap<String,Any>,
                      callback: (Boolean, String) -> Unit)

    fun deleteFoodItem(productId: String,
                      callback: (Boolean, String) -> Unit)

    fun fetchFoodItemById(productId: String,
                       callback: (ProductModel?,Boolean, String) -> Unit)

    fun fetchFoodItem(callback: (List<ProductModel>?,Boolean, String) -> Unit)

    fun getFoodItemById(productId: String, callback: (ProductModel?, Boolean, String) -> Unit)
    fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit)

    fun getFileNameFromUri(context: Context, uri: Uri): String?
}