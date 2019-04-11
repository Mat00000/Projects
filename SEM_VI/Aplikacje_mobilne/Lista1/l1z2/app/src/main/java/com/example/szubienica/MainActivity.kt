package com.example.szubienica

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

//    private val words = listOf<String>("UGANDA", "KOT", "POLITECHNIKA", "ANDROID", "KAPITAN", "STUDIO",
//                                        "TELEWIZJA", "DRZEWO", "BUDYNEK", "ORACLE")
    private lateinit var words: Array<String>
    private var choosenWord = ""
    private var hiddenWord = ""
    private var wordLetters = CharArray(0)
    private var hiddenLetters = CharArray(0)
    private var countOfGuessedLetters = 0
    private var resultGame = 0
    private var countToDeath = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.resultGame).text = "Wynik: $resultGame"
        words = resources.getStringArray(R.array.words)
    }

    fun randomWord(view: View) {
        if(countToDeath == 7) {
            newGame()
        }
        if(countOfGuessedLetters != 0) {
            Toast.makeText(this, "Musisz odkryć całe słowo! -_-", Toast.LENGTH_LONG).show()
        }
        else {
            doRandomWord()
        }
    }

    fun checkLetter(view: View) {
        var existLetter = false
        val letter = findViewById<EditText>(R.id.letterInput).text.toString()
        findViewById<EditText>(R.id.letterInput).setText("")
        if(letter == "") {
            Toast.makeText(this, "Wpisz literę!", Toast.LENGTH_SHORT).show()
            return
        }
        if(countOfGuessedLetters == 0) {
            warning("Wylosuj hasło, aby spróbować swoich sił!")
            return
        }

        hiddenLetters.forEach {
            if(it.toString() == letter) {
                Toast.makeText(this, "$letter już odkryłeś! ;)", Toast.LENGTH_SHORT).show()
                return
            }
        }

        wordLetters.forEachIndexed { index, it ->
            if (it.toString() == letter) {
                hiddenLetters[index] = letter.single()
                hiddenWord = String(hiddenLetters)
                countOfGuessedLetters--
                existLetter = true
                changeResult(true)
            }
        }

        if(existLetter) {
            findViewById<TextView>(R.id.Haslo).text = "$hiddenWord"
            Toast.makeText(this, "$letter zawiera się :)", Toast.LENGTH_SHORT).show()
            println(countOfGuessedLetters)
            if(countOfGuessedLetters == 0) {
                discloseWord()
            }
        }
        else {
            changeResult(false)
            Toast.makeText(this, "$letter nie zawiera się :(", Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeResult(boolean: Boolean) {
        val button = findViewById<Button>(R.id.resultGame)
        if(boolean) {
            resultGame++
            button.setBackgroundColor(Color.GREEN)
        }
        else {
            resultGame--
            button.setBackgroundColor(Color.RED)
            changeImage()
        }
        button.text = "Wynik: $resultGame"
    }

    private fun changeImage() {
        countToDeath++
        when(countToDeath) {
            1 -> imageView.setImageResource(R.drawable.w_1)
            2 -> imageView.setImageResource(R.drawable.w_2)
            3 -> imageView.setImageResource(R.drawable.w_3)
            4 -> imageView.setImageResource(R.drawable.w_4)
            5 -> imageView.setImageResource(R.drawable.w_5)
            6 -> imageView.setImageResource(R.drawable.w_6)
            7 -> imageView.setImageResource(R.drawable.w_7)
        }
        if(countToDeath == 7) {
            var builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Zawisłeś...")
                setMessage("Twój wynik to $resultGame. Niech Bogowie mają Cię w swojej opiece!")
                setNegativeButton("Nowy żywot", {_, _ -> newGame()})
                show()
            }
        }
    }

    private fun newGame() {
        resultGame = 0
        countToDeath = 0
        imageView.setImageResource(R.drawable.w_0)
        doRandomWord()
        findViewById<Button>(R.id.resultGame).text = "Wynik: $resultGame"
        findViewById<Button>(R.id.resultGame).setBackgroundColor(Color.rgb(51, 181, 229))
    }

    private fun warning(string: String) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Nie umiesz grać?")
            setMessage(string)
            setNegativeButton("Przepraszam", null)
            show()
        }
    }

    private fun discloseWord () {
        val builder = AlertDialog.Builder(this)
        resultGame += 5
        findViewById<Button>(R.id.resultGame).text = "Wynik: $resultGame"
        with(builder) {
            setTitle("Udało Ci się!")
            setMessage("Udało Ci się!\nZdobywasz dodatkowe 5 punktów.\nChcesz spróbować odkryć nowe hasło?")
            setPositiveButton("Spróbuj", { dialog, which -> doRandomWord() })
            setNegativeButton("Może później", null)
            show()
        }
    }

    private fun doRandomWord() {
        wordLetters = CharArray(0)
        hiddenWord = ""
        val randomInt = Random()
        choosenWord = words.get(randomInt.nextInt(10))
        wordLetters = choosenWord.toCharArray()
        wordLetters.forEach {
            hiddenWord = hiddenWord.plus("*")
            if(countOfGuessedLetters == -1) {
                countOfGuessedLetters = 0
            }
            countOfGuessedLetters++
        }
        hiddenLetters = hiddenWord.toCharArray()
        findViewById<TextView>(R.id.Haslo).text = "$hiddenWord"
    }
}
