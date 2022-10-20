package com.vanapic.mobiledeveloperintern2022_dimasardiansyah.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.vanapic.mobiledeveloperintern2022_dimasardiansyah.databinding.ActivitySecondScreenBinding
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var name: TextView
    private lateinit var selectedName: TextView
    private lateinit var buttonChoose: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Second Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        name = binding.tvName
        selectedName = binding.tvSelectedUsername
        buttonChoose = binding.btnChooseUser

        val getName = intent?.getStringExtra(EXTRA_NAME) as String
        name.text = getName

        buttonChoose.setOnClickListener {
            startActivity(Intent(this@SecondScreen, ThirdScreen::class.java))
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(tempReceiver, IntentFilter("data"));
    }

    private val tempReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent?) {
            val value = intent!!.getStringExtra("VALUE")
            selectedName.text = "Selected $value"
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    override fun onDestroy() {
        super.onDestroy()
        tempReceiver.debugUnregister
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}