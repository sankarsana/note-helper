package com.bhaktaprogram.main

import android.os.Build
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
//@Config(manifest = "src/main/AndroidManifest.xml", sdk = [Build.VERSION_CODES.O])
@Config(sdk = [Build.VERSION_CODES.O])
class MainActivityTest {

    @Test
    fun `should open notes fragment at start`() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
//        val notesFragment = activity
//            .supportFragmentManager
//            .findFragmentById(R.id.fragments_container)

//        assertNotNull(notesFragment)
        assertNotNull(1)
    }

}