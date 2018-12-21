package com.example.fif.kade3

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerViewAccessibilityDelegate
import android.util.Log
import com.example.fif.kade3.View.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.fif.kade3.R.id.*

@RunWith(AndroidJUnit4::class)
class SubTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(teams_menu)).check(matches(isDisplayed()))
        onView(withId(teams_menu)).perform(click())

        Thread.sleep(3000)
        onView(withId(rvListTeam)).check(matches(isDisplayed()))
        onView(withId(rvListTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click())
        )
        Thread.sleep(3000)
        onView(withId(favorites_team))
            .check(matches(isDisplayed()))
        onView(withId(favorites_team)).perform(click())
        onView(withText("Tim telah ditambahkan")).check(matches(isDisplayed()))

        pressBack()

        onView(withId(favorites_menu)).check(matches(isDisplayed()))
        onView(withId(favorites_menu)).perform(click())
        onView(withId(main_container)).perform(swipeLeft())
        Thread.sleep(3000)
        onView(withId(rvListTeam)).check(matches(isDisplayed()))
        onView(withId(rvListTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        Thread.sleep(3000)
        onView(withId(favorites_team)).check(matches(isDisplayed()))
        onView(withId(favorites_team)).perform(click())
        onView(withText("Tim telah dihapuskan")).check(matches(isDisplayed()))

    }
}