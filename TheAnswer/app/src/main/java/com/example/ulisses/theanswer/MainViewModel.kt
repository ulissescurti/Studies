package com.example.ulisses.theanswer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    var selectedCalculation = -1
    var scoreCorrect = MutableLiveData<Int>().apply { value = 0 }
    var scoreWrong = MutableLiveData<Int>().apply { value = 0 }
    var x = MutableLiveData<Int>().apply { value = 0 }
    var y = MutableLiveData<Int>().apply { value = 0 }
    var question = MutableLiveData<String>().apply { value = "" }
    var result: Int = 0

    fun updateCorrect() {
        update(scoreCorrect)
    }

    fun updateWrong() {
        update(scoreWrong)
    }

    private fun update(score: MutableLiveData<Int>) {
        val value = score.value ?: 0
        score.value = value + 1
    }

    fun updateX(value: Int) {
        x.value = value
    }

    fun updateY(value: Int) {
        y.value = value
    }

    fun getX(): Int {
        return x.value!!
    }

    fun getY(): Int {
        return y.value!!
    }

    fun updateResult(value: Int) {
        result = value
    }

    fun updateQuestion(value: String) {
        question.value = value
    }

}
