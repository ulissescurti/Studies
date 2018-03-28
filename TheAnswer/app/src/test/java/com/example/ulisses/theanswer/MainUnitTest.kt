package com.example.ulisses.theanswer

import android.arch.lifecycle.ViewModelProviders
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainUnitTest {

    @Test
    fun testUpdates() {
        val viewModel = getViewModel()

        viewModel.updateCorrect()
        assertEquals(1, viewModel.scoreCorrect.value)

        viewModel.updateWrong()
        assertEquals(1, viewModel.scoreWrong.value)

        viewModel.updateX(22)
        assertEquals(22, viewModel.x.value)

        viewModel.updateY(33)
        assertEquals(33, viewModel.y.value)

        viewModel.updateQuestion("abc")
        assertEquals("abc", viewModel.question.value)

        viewModel.updateResult(123)
        assertEquals("123", viewModel.result)
    }

    private fun getViewModel(): MainViewModel {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        return ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

}
