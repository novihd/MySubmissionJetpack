package com.example.mysubmissionjetpack1.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE: String = "GLOBAL"

    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingResource.increment()

    fun decrement() = espressoIdlingResource.decrement()


}
