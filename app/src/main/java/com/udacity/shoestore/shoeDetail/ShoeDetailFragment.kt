package com.udacity.shoestore.shoeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var binding: ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        binding.shoedetailViewModel = viewModel

        binding.saveBtn.setOnClickListener {
            if(binding.editTextTextSize.text.toString().isNotEmpty()){
                saveShoe(
                    Shoe(
                        binding.editTextTextName.text.toString(),
                        binding.editTextTextSize.text.toString().toDouble(),
                        binding.editTextTextCompany.text.toString(),
                        binding.editTextTextDescription.text.toString(),
                        binding.editTextImages.text.toString().split(",")
                    )
                )
            }
        }
        binding.cancelBtn.setOnClickListener {
            backPress()
        }
        return binding.root
    }

    fun saveShoe(shoe: Shoe) {
        val action =
            ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment(shoe)
        NavHostFragment.findNavController(this).navigate(action)
    }

    fun backPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}