package com.example.fooddelivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.model.ProductModel
import com.example.fooddelivery.ui.activity.UpdateProductActivity
import java.util.ArrayList

class ProductAdapter(
    var context: Context,
    var data : ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        val pName : TextView = itemView.findViewById(R.id.displayName)
        val pPrice : TextView = itemView.findViewById(R.id.displayPrice)
        val pDesc : TextView = itemView.findViewById(R.id.displayDesc)
        val edit:TextView=itemView.findViewById(R.id.Edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val itemView : View = LayoutInflater.from(context)
            .inflate(R.layout.sampleproduct,parent,false)
        return ProductViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.pName.text = data[position].productName
        holder.pPrice.text = data[position].productId.toString()
        holder.pDesc.text = data[position].productDesc
        holder.edit.setOnClickListener{
            val intent= Intent(context,
                UpdateProductActivity::class.java)
            intent.putExtra("productId",data[position].productId)
            context.startActivity(intent)
        }
    }

    fun updateData(products : List<ProductModel>){
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }
    fun getProductId(position:Int):String{
        return data[position].productId
    }
}