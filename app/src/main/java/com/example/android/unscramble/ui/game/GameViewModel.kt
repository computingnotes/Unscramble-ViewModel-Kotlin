package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {


    private var _score = 0
    val score : Int
        get() = _score  //adding backing property to score

    private var _currentWordCount = 0
    val currentWordCount: Int
        get() = _currentWordCount
//    private var _currentScrambledWord = "test"

    private lateinit var _currentScrambledWord: String

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String




    val currentScrambleWord: String get () = _currentScrambledWord


    fun reinitializeData(){
        _score = 0
        _currentWordCount = 0
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
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)

        }


    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
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

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed")
    }




    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

}