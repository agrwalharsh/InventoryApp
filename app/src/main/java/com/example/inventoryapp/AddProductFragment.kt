package com.example.inventoryapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoryapp.databinding.AddProductFragmentBinding
import com.example.inventoryapp.db.Product.Product
import com.example.inventoryapp.db.Product.ProductDao
import com.example.inventoryapp.db.Product.ProductDataBase
import com.example.inventoryapp.db.Product.ProductRepository

class AddProductFragment : Fragment() {

    private lateinit var binding: AddProductFragmentBinding
    private lateinit var productViewModel: ProductViewModel

    companion object {
        fun newInstance(): AddProductFragment {
            return AddProductFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.add_product_fragment, container, false)
        val dao: ProductDao =
            ProductDataBase.getDatabase(requireActivity().applicationContext).productDao()
        val repository = ProductRepository(dao)
        val factory = ProductViewModelFactory(repository)
        productViewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]
        binding.myViewModel = productViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        productViewModel.message.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun initRecyclerView() {
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        displayProductsList()
    }

    private fun displayProductsList() {
        productViewModel.productList.observe(viewLifecycleOwner) {
            Log.i("MYTAG", it.toString())
            binding.productRecyclerView.adapter = ProductAdapter(it, {selectedItem : Product -> listItemClicked(selectedItem)})
        }
    }

    private fun listItemClicked(product: Product) {
        productViewModel.initUpdateAndDelete(product)
    }
}