package com.example.todoapp

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ExampleUnitTest {

    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}
