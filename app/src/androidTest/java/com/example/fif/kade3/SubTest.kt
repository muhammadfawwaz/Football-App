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
    fun testRecyclerViewBehaviour() {
        onView(withId(viewPagerId)).check(matches(isDisplayed()))
        onView(withId(viewPagerId)).perform(swipeLeft())

        Thread.sleep(3000)
        onView(withId(rvNextMatchId)).check(matches(isDisplayed()))
        onView(withId(rvNextMatchId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
    }

    @Test
    fun testAppBehaviour() {
        Thread.sleep(3000)
        onView(withId(viewPagerId)).check(matches(isDisplayed()))

        onView(withId(rvLastMatchId)).check(matches(isDisplayed()))
        onView(withId(rvLastMatchId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        Thread.sleep(3000)
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Pertandingan telah ditambahkan")).check(matches(isDisplayed()))

        pressBack()

        onView(withId(viewPagerId)).perform(swipeLeft()).perform(swipeLeft())

        Thread.sleep(3000)
        onView(withId(rvFavorite)).check(matches(isDisplayed()))
        onView(withId(rvFavorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        Thread.sleep(3000)
        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Pertandingan telah dihapuskan")).check(matches(isDisplayed()))

    }
}