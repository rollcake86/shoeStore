package com.udacity.shoestore.shoeListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListingViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<ArrayList<Shoe>>()
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = ArrayList()

        for (i: Int in 1..20) {
            val shoe = Shoe(
                "aa$i",
                240.0,
                "google",
                "goole shoe",
                arrayListOf("image1", "image2", "image3")
            )
            _shoeList.value?.add(shoe)
        }
    }

}