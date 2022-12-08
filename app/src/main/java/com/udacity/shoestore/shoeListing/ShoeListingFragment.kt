package com.udacity.shoestore.shoeListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding
import timber.log.Timber


class ShoeListingFragment : Fragment() {
    private lateinit var viewModel: ShoeListingViewModel
    private lateinit var binding: ShoeListingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_listing_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ShoeListingViewModel::class.java)
        binding.shoelistingViewModel = viewModel


        viewModel.shoeList.observe(viewLifecycleOwner, Observer { it ->

            it.forEach { shoe ->
                val text = TextView(this.context)
                text.setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                )
                Timber.i(shoe.name)
                text.setText(shoe.name)
                binding.listItem.addView(text)
            }
        })



        return binding.root
    }

}