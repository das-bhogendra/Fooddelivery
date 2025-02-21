package com.example.fooddelivery.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.fooddelivery.model.ProductModel
import com.example.fooddelivery.repository.ProductRepository



class ProductViewModel(val repo : ProductRepository) {
    fun addFoodItem(
        productModel: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addFoodItem(productModel, callback)
    }

    fun updateFoodItem(
        productId: String,
        data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    ) {
        repo.updateFoodItem(productId, data, callback)
    }

    fun deleteFoodItem(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.deleteFoodItem(productId, callback)
    }

    var _products = MutableLiveData<ProductModel>()
    var products = MutableLiveData<ProductModel>()
        get() = _products

    var _allProducts = MutableLiveData<List<ProductModel>>()
    var allProducts = MutableLiveData<List<ProductModel>>()
        get() = _allProducts


    fun fetchFoodItemById(productId: String){
        repo.fetchFoodItemById(productId){
                products,success,message->
            if(success){
                _products.value = products
            }
        }
    }

    var _loading = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
        get() = _loading
    fun fetchFoodItem() {
        _loading.value = true
        repo.fetchFoodItem(){
                products,success,message->
            if(success){
                _allProducts.value = products
                _loading.value = false
            }
        }
    }

    fun uploadImage(context: android.content.Context, imageUri: Uri, callback: (String?) -> Unit){
        repo.uploadImage(context, imageUri, callback)
    }


}