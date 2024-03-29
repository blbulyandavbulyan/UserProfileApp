package com.blbulyandavbulan.userprofileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blbulyandavbulan.userprofileapp.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userScore = Random.nextInt(101)
        binding.scoreIndicator.setProgress(userScore, true)
        binding.scoreValueTooltip.text = getString(R.string.score_value_template, userScore)
    }
}