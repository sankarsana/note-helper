package com.bhaktaprogram.coreapi.extensions

import androidx.fragment.app.FragmentActivity
import com.bhaktaprogram.coreapi.AppFacade

fun FragmentActivity.getProvidersFacade() = (application as AppFacade).getProvidersFacade()