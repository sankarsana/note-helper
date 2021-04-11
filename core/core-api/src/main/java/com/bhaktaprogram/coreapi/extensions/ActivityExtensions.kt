package com.bhaktaprogram.coreapi.extensions

import androidx.fragment.app.Fragment
import com.bhaktaprogram.coreapi.AppFacade

fun Fragment.getAppFacade() = requireActivity().application as AppFacade