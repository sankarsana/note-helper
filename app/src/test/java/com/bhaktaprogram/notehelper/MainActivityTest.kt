package com.bhaktaprogram.notehelper

import android.os.Build
import com.bhaktaprogram.main.MainActivity
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O])
class MainActivityTest {

    @Test
    fun `should open notes fragment at start`() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).setup().get()
        val notesFragment = activity
            .supportFragmentManager
            .findFragmentById(R.id.fragments_container)

        assertNotNull(notesFragment)
    }

}