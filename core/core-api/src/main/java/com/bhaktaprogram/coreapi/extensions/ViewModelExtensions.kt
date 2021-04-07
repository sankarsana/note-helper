package com.bhaktaprogram.coreapi.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

fun <T : ViewModel> ViewModelProvider.Factory.get(
    owner: ViewModelStoreOwner,
    modelClass: Class<T>
) = ViewModelProvider(owner, this).get(modelClass)