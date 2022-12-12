package com.udacity.shoestore.shoeListing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreApplication
import com.udacity.shoestore.databinding.ShoeListingFragmentBinding
import com.udacity.shoestore.login.LoginFragmentDirections
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.onboarding.OnboardingFragmentArgs
import kotlinx.android.synthetic.main.activity_main.*
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

        activity!!.toolbar.addView(createLogoutBtn())

        val shoe = ShoeListingFragmentArgs.fromBundle(arguments!!).shoe
        if(shoe.name.isNotEmpty()){
            viewModel.shoeList.value!!.add(shoe)
        }
        binding.floatingActionButton.setOnClickListener {
            showDetailShoe()
        }
        val inflater = LayoutInflater.from(context)
        viewModel.shoeList.value!!.forEach {
            val itemView = inflater.inflate(R.layout.list_item, null)
            val shoeNameView = itemView.findViewById<TextView>(R.id.shoe_name)
            val companyView = itemView.findViewById<TextView>(R.id.company_name)
            shoeNameView.text = it.name
            companyView.text = it.company
            binding.listItem.addView(itemView)
        }
        return binding.root
    }

    private fun createLogoutBtn(): View? {
        val button = Button(context)
        button.text = "logout"
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,ViewGroup.LayoutParams.WRAP_CONTENT )
        button.layoutParams = lp
        button.setOnClickListener {
            logout()
        }
        button.id = ViewCompat.generateViewId()
        return button
    }

    private fun removeLogoutBtn(){
        activity!!.toolbar.removeAllViews()
    }

    fun showDetailShoe(){
        val action = ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
        findNavController(this).navigate(action)
    }
    fun logout(){
        val application = context!!.applicationContext as ShoeStoreApplication
        application.loginCheck = false
        removeLogoutBtn()
        val action = ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment()
        findNavController(this).navigate(action)
    }

}