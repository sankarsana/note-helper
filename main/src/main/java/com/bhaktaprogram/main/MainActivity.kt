package com.bhaktaprogram.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.coreapi.extensions.getProviderFacade
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var viewModel: MainViewModel

    private val navigator = AppNavigator(this, R.id.fragments_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainComponent.create(getProviderFacade()).inject(this)

        if (savedInstanceState == null) {
            viewModel.onOpened()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}