package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
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

        viewModel.emptyCheck.observe(viewLifecycleOwner , Observer { it ->
//            if(it){
//                Toast.makeText(this.context ,"Email&Password Empty!" , Toast.LENGTH_SHORT).show()
//            } else {
                nextStep()
//            }
        })
        return binding.root
    }

    fun nextStep(){
        val action = LoginFragmentDirections.actionLoginFragmentToOnboardingFragment(0)
        NavHostFragment.findNavController(this).navigate(action)
    }
}