package com.example.notesvsshoppinglist

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun bottomNavigation_checklistFragmentClick() {
        onView(withId(R.id.navigation_checklist)).perform(click())
        onView(withText("This is dashboard Fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_notesFragmentClick() {
        onView(withId(R.id.navigation_notes)).perform(click())
        onView(withText("This is home Fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_calendarFragmentClick() {
        onView(withId(R.id.navigation_calendar)).perform(click())
        onView(withText("This is notifications Fragment")).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_settingsFragmentClick() {
        onView(withId(R.id.navigation_settings)).perform(click())
        onView(withText("This is Setting Fragment")).check(matches(isDisplayed()))
    }

    @After
    fun close() {
        scenario.close()
    }
}
