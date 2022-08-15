package com.example.notesvsshoppinglist.checklist_test

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.atPosition
import com.example.notesvsshoppinglist.getCurrentDateTime_test
import com.example.notesvsshoppinglist.ui.checklist.EditChecklistFragment
import com.example.notesvsshoppinglist.ui.checklist.TaskAdapter
import com.example.notesvsshoppinglist.ui.notes.EditNotesFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditChecklistFragmentTest {

    private lateinit var scenario: FragmentScenario<EditChecklistFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun date_header_test_is_displayed() {
        onView(withId(R.id.date_header))
            .check(matches(isDisplayed()))
    }

    @Test
    fun date_matches_the_software_test() {
        val date = getCurrentDateTime_test()
        onView(withId(R.id.date))
            .check(matches(withText(date)))
    }

    @Test
    fun name_header_test_is_displayed() {
        onView(withId(R.id.name_header))
            .check(matches(isDisplayed()))
    }

    @Test
    fun description_header_test_is_displayed() {
        onView(withId(R.id.description_header))
            .check(matches(isDisplayed()))
    }

    @Test
    fun name_test_is_filling() {
        onView(withId(R.id.name))
            .check(matches(withText("")))

        val testText = "Проверка ввода текста"

        onView(withId(R.id.name)).perform(click())
        onView(withId(R.id.name)).perform(replaceText(testText), closeSoftKeyboard())
        onView(withId(R.id.name)).check(matches(withText(testText)))
    }

    @Test
    fun description_test_is_filling() {
        onView(withId(R.id.description))
            .check(matches(withText("")))

        val testText = "Проверка ввода текста"

        onView(withId(R.id.description)).perform(click())
        onView(withId(R.id.description))
            .perform(replaceText(testText), closeSoftKeyboard())
        onView(withId(R.id.description))
            .check(matches(withText(testText)))
    }

    @Test
    fun recycler_header_test_is_displayed() {
        onView(withId(R.id.recycler_header)).check(matches(isDisplayed()))
    }

}