package com.bhaktaprogram.edit_note.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bhaktaprogram.edit_note.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EditNotesFragmentTest {

    @Test
    fun should_show_toast() {
        val scenario = launchFragmentInContainer<EditNotesFragment>()

        onView(withId(R.id.title))
            .perform(ViewActions.pressBack())
            .check(matches(withText("")))
    }
}