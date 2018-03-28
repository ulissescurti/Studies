package com.example.ulisses.theanswer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ulisses.theanswer.MainActivity.Calculation.MINUS
import com.example.ulisses.theanswer.MainActivity.Calculation.PLUS
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    object Calculation {
        const val PLUS = 0
        const val MINUS = 1
    }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.scoreCorrect.observe(this, Observer { value ->
            textViewCorrect.text = value.toString()
        })
        mViewModel.scoreWrong.observe(this, Observer { value ->
            textViewWrong.text = value.toString()
        })
        mViewModel.question.observe(this, Observer { value ->
            textViewQuestion.text = value
        })

        if (mViewModel.selectedCalculation == -1) {
            randomizeQuestion()
        }

        buttonGo.setOnClickListener {
            onNextQuestion()
        }
    }

    private fun onNextQuestion() {
        try {
            if (mViewModel.result == editTextAnswer.text.toString().toInt()) {
                mViewModel.updateCorrect()
            } else {
                mViewModel.updateWrong()
            }
        } catch (ex: Throwable) {
            mViewModel.updateWrong()
        }
        randomizeQuestion()
    }

    private fun randomizeQuestion() {
        mViewModel.updateX(random(1, 1000))
        mViewModel.updateY(random(1, 1000))
        mViewModel.selectedCalculation = random(0, 1)
        when (mViewModel.selectedCalculation) {
            PLUS -> {
                mViewModel.updateQuestion(getString(R.string.question_plus).format(mViewModel.getX(), mViewModel.getY()))
                mViewModel.updateResult(mViewModel.getX().plus(mViewModel.getY()))
            }
            MINUS -> {
                mViewModel.updateQuestion(getString(R.string.question_minus).format(mViewModel.getX(), mViewModel.getY()))
                mViewModel.updateResult(mViewModel.getX().minus(mViewModel.getY()))
            }
        }
        editTextAnswer.setText("")
    }

    private fun random(from: Int, to: Int): Int {
        return Random().nextInt(to + 1 - from) + from
    }

}
