package com.bhaktaprogram.edit_note

import android.app.Application
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade
import io.mockk.mockk

class TestApp : Application(), AppFacade {

    override fun getProvidersFacade(): ProvidersFacade {
        return mockk()
    }

}