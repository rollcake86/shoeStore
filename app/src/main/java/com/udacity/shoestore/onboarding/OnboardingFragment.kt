package com.udacity.shoestore.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.OnboardingFragmentBinding

class OnboardingFragment : Fragment() {
    private lateinit var viewModel: OnboardingViewModel
    private lateinit var binding: OnboardingFragmentBinding
    private var kind = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.onboarding_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(OnboardingViewModel::class.java)
        binding.onboardingViewModel = viewModel

        if(OnboardingFragmentArgs.fromBundle(arguments!!).kind == 0){
            this.kind = 0
            binding.textView3.setText("WelCome")
            binding.textView4.setText("WelComeWelComeWelComeWelCome")
            binding.button.setText("Instructions")
        } else {
            this.kind = 1
            binding.textView3.setText("Instructions")
            binding.textView4.setText("InstructionsInstructionsInstructions")
            binding.button.setText("Next")
        }

        binding.button.setOnClickListener {
            if(kind == 0){
                goInstructions()
            } else {
                goNext()
            }
        }
        return binding.root
    }

    fun goInstructions(){
        val action = OnboardingFragmentDirections.actionOnboardingFragmentSelf(1)
        NavHostFragment.findNavController(this).navigate(action)
    }
    fun goNext(){
        val action = OnboardingFragmentDirections.actionOnboardingFragmentToShoeListingFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}