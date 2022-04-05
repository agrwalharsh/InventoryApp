package com.example.inventoryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.databinding.ListItemBinding
import com.example.inventoryapp.db.Product.Product

class ProductAdapter(
    private val products: List<Product>,
    private val clickListener: (Product) -> Unit
) : RecyclerView.Adapter<myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

}

class myViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product, clickListener: (Product) -> Unit) {
        binding.nameTextview.text = product.name
        binding.quantityTextview.text = product.quantity.toString()
        binding.listItemLayout.setOnClickListener {
            clickListener(product)
        }
    }
}