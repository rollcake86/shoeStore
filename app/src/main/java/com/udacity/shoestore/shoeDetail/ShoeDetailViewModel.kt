package com.udacity.shoestore.shoeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel : ViewModel() {

    private val shoe = MutableLiveData<Shoe>()
    val _shoe: LiveData<Shoe>
        get() = shoe

}