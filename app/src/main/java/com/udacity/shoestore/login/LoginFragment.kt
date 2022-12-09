package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreApplication
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment()  {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        binding.editTextTextEmailAddress.addTextChangedListener {
            viewModel.email.postValue(it.toString())
        }
        binding.editTextTextPassword.addTextChangedListener {
            viewModel.password.postValue(it.toString())
        }

        viewModel.emptyCheck.observe(viewLifecycleOwner , Observer {
            if(it){
                Toast.makeText(this.context ,"Email&Password Empty!" , Toast.LENGTH_SHORT).show()
            } else {
                val application = context!!.applicationContext as ShoeStoreApplication
                application.loginCheck = true
                nextStep()
            }
        })
        return binding.root
    }

    fun nextStep(){
        val action = LoginFragmentDirections.actionLoginFragmentToOnboardingFragment(0)
        NavHostFragment.findNavController(this).navigate(action)
    }
}