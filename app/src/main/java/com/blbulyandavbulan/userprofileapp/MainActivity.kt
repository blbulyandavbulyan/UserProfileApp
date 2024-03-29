package com.blbulyandavbulan.userprofileapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
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
        binding.notificationSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            binding.notificationsCheckboxes.allViews.forEach {
                if (isChecked) it.isEnabled = true
                else if (it is CheckBox) {
                    it.isEnabled = false
                    it.isChecked = false
                }
            }
        }

    }
}