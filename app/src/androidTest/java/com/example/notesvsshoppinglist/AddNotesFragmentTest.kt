package com.example.notesvsshoppinglist

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AddNotesFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun floatingActionButton_testIsDisplayed(){
        onView(withId(R.id.fab_notes)).check(matches(isDisplayed()))
    }

    @Test
    fun add_note_test(){
        onView(withId(R.id.fab_notes)).perform(click())

        val nameNote = "Интересный факт"
        val descriptionNote = "В среднем самые высокие люди – голландцы."

        onView(withId(R.id.name)).perform(click())
        onView(withId(R.id.name)).perform(replaceText(nameNote), closeSoftKeyboard())

        onView(withId(R.id.description)).perform(click())
        onView(withId(R.id.description)).perform(
            ViewActions.replaceText(descriptionNote),
            ViewActions.closeSoftKeyboard()
        )

        onView(withId(R.id.toolbar)).perform(pressBack())

        assertNotNull(
            onView(withText(nameNote)).check(matches(isDisplayed()))
        )
    }



    /**
     *
     * Не получается реализовать нажатие кнопки в toolbar
     *
     * */
    @Test
    fun delete_note_test(){

        val nameNote = "Интересный факт"

        assertNotNull(
            onView(withText(nameNote)).check(matches(isDisplayed()))
        )

        onView(withText(nameNote)).perform(click())

        assertNull(
            onView(withText(nameNote)).check(matches(isDisplayed()))
        )
    }

    @After
    fun close() {
        scenario.close()
    }
}