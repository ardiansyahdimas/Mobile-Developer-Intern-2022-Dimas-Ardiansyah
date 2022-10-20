package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    private lateinit var buttonCheck: Button
    private lateinit var buttonNext: Button
    private lateinit var edtName: EditText
    private lateinit var edtPalindrome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""
        supportActionBar?.hide()
        buttonCheck = binding.btnCheck
        buttonNext = binding.btnNext
        edtName = binding.edtName
        edtPalindrome = binding.edtPolindrome

        buttonCheck.setOnClickListener {
            val text = edtPalindrome.text.toString()
            when {
                text.isEmpty() -> {
                    edtPalindrome.error = "Please enter sentence text"
                    edtPalindrome.requestFocus()
                }
                else -> {
                    if (checkPalindrome(text)) Toast.makeText(
                        this,
                        "isPalindrome",
                        Toast.LENGTH_SHORT
                    ).show() else Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonNext.setOnClickListener {
            val name = edtName.text.toString()
            when {
                name.isEmpty() -> {
                    edtName.error = "Please enter your name!"
                    edtName.requestFocus()
                } else -> {
                    val intent = Intent(this@FirstScreen, SecondScreen::class.java)
                    intent.putExtra(SecondScreen.EXTRA_NAME, name)
                    startActivity(intent)
                }
            }
        }
    }

    private fun checkPalindrome(text: String): Boolean {
        var l = 0
        var h = text.length - 1
        val _text = text.lowercase()
        while (l <= h) {
            val getAtl = _text[l]
            val getAth = _text[h]
            if (!(getAtl >= 'a' && getAtl <= 'z')) l++ else if (!(getAth >= 'a' && getAth <= 'z')) h-- else if (getAtl == getAth) {
                l++
                h--
            } else return false
        }
        return true
    }
}