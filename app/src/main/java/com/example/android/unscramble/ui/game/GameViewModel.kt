package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {



    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
    get() = _score


    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
    get() = _currentWordCount
//
//    private var _score = 0
//    val score : Int
//        get() = _score  //adding backing property to score
//
//    private var _currentWordCount = 0
//    val currentWordCount: Int
//        get() = _currentWordCount
//    private var _currentScrambledWord = "test"

//    private lateinit var _currentScrambledWord: String

    private val _currentScrambledWord = MutableLiveData<String>()

    val currentScrambledWord: LiveData<String>
    get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String




//    val currentScrambleWord: String get () = _currentScrambledWord



    fun reinitializeData(){
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

//    Updates current word and currentscrambled with the next word
    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while(String(tempWord).equals(currentWord,false)){
            tempWord.shuffle()
        }

        if(wordsList.contains(currentWord)){
            getNextWord()
        }
        else {
//            _currentScrambledWord = String(tempWord)
            _currentScrambledWord.value = String(tempWord) //To access data within Live Data
            _currentWordCount. value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)

        }


    }

    private fun increaseScore() {
//        _score.value += SCORE_INCREASE
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }


    fun isUserWordCorrect(playerWord: String):Boolean {
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }


    init {
        Log.d("GameFragment", "GameViewModel Created!")
        getNextWord()
    }

//    override fun onCleared() {
//        super.onCleared()
//        Log.d("GameFragment", "GameViewModel destroyed")
//    }




    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

}