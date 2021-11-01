package com.example.mysubmissionjetpack1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.mysubmissionjetpack1.utils.EspressoIdlingResource.espressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(espressoIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            18
        ))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.img_detail)).check((matches(isDisplayed())))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadSeries() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tv)).perform(click())

        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            18
        ))
    }

    @Test
    fun loadDetailSeries() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tv)).perform(click())

        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.img_detail)).check((matches(isDisplayed())))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_star_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed()))

    }

    @Test
    fun loadFavMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isClickable()))
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))

        onView(withId(R.id.img_movie_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_rating_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_date_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadFavSeries() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isClickable()))
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(click())
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.img_tv_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_title_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_rating_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_date_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun btnSortMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isClickable()))
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_newest)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_newest)).check(matches(isClickable()))
        onView(withId(R.id.mov_newest)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_oldest)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_oldest)).check(matches(isClickable()))
        onView(withId(R.id.mov_oldest)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
    }

    @Test
    fun btnSortSeries() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        onView(withId(R.id.btn_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).check(matches(isClickable()))
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(click())
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.ser_newest)).check(matches(isDisplayed()))
        onView(withId(R.id.ser_newest)).check(matches(isClickable()))
        onView(withId(R.id.ser_newest)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.ser_oldest)).check(matches(isDisplayed()))
        onView(withId(R.id.ser_oldest)).check(matches(isClickable()))
        onView(withId(R.id.ser_oldest)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
    }

}