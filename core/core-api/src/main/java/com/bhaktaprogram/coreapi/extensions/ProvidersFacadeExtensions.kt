package com.bhaktaprogram.coreapi.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade

fun Context.getProviderFacade(): ProvidersFacade {
    return when (this) {
        is AppFacade -> getProvidersFacade()
        else -> this.applicationContext.getProviderFacade()
    }
}

fun Fragment.getProviderFacade(): ProvidersFacade {
    return requireContext().getProviderFacade()
}