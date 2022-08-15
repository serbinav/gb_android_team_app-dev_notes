package com.example.notesvsshoppinglist.checklist_test

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notesvsshoppinglist.*
import com.example.notesvsshoppinglist.ui.checklist.TaskAdapter
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddChecklistFragmentTest {

    private val taskText = taskRandom()
    private val nameNote = nameRandom()
    private val descriptionNote = descriptionRandom()

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun floatingActionButton_testIsDisplayed(){
        onView(withId(R.id.navigation_checklist)).perform(click())
        onView(withId(R.id.fab_checklist)).check(matches(isDisplayed()))
    }

    @Test
    fun add_delete_checklist_test(){
        onView(withId(R.id.navigation_checklist)).perform(click())

        onView(withId(R.id.fab_checklist)).perform(click())

        onView(withId(R.id.name)).perform(click())
        onView(withId(R.id.name)).perform(replaceText(nameNote), closeSoftKeyboard())

        onView(withId(R.id.description)).perform(click())
        onView(withId(R.id.description)).perform(
            replaceText(descriptionNote),
            closeSoftKeyboard()
        )

        add_task_test()
        task_test_is_checked()
        delete_task_test()

        onView(withId(R.id.toolbar)).perform(pressBack())

        onView(withText(nameNote)).check(matches(isDisplayed()))

        delete_checklist_test()
    }


    private fun delete_checklist_test(){
        onView(withText(nameNote)).check(matches(isDisplayed()))

        onView(withText(nameNote)).perform(click())

        onView(withId(R.id.action_delete)).perform(click())

        onView(withText(nameNote)).check(doesNotExist())
    }

    private fun add_task_test() {
        onView(withId(R.id.button_add_task)).perform(click())

        onView(withId(R.id.edit_elem)).perform(replaceText(taskText), closeSoftKeyboard())
        onView(withText(R.string.alert_positive_btn)).perform(click())

        onView(withText(taskText)).check(matches(isDisplayed()))
    }

    private fun task_test_is_checked(){
        onView(allOf(withText(taskText), withId(R.id.name)))
            .check(matches(isNotChecked()))
            .perform(click())
            .check(matches(isChecked()))

    }

    private fun delete_task_test(){
        onView(withId(R.id.recycler_checklist)).perform(RecyclerViewActions.actionOnItemAtPosition<TaskAdapter.TaskViewHolder>(0, longClick()))
        onView(withText(R.string.delete)).perform(click())

        onView(withText(taskText)).check(doesNotExist())
    }

    @After
    fun close() {
        scenario.close()
    }

}