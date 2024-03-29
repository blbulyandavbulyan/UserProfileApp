package com.blbulyandavbulan.userprofileapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.core.widget.addTextChangedListener
import com.blbulyandavbulan.userprofileapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var authorizationNotificationsEnabled = false
    private var notificationsSwitchEnabled = false
    private var newProductsAndOffersNotificationsEnabled = false
    private var nameFieldValid = false
    private var phoneFieldValid = false
    private var validGenderSelected = false;
    private var saveButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userScore = Random.nextInt(101)
        binding.scoreIndicator.setProgress(userScore, true)
        binding.scoreValueTooltip.text = getString(R.string.score_value_template, userScore)
        saveButton = binding.saveButton
        binding.notificationSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            binding.notificationsCheckboxes.allViews.forEach {
                if (isChecked) it.isEnabled = true
                else if (it is CheckBox) {
                    it.isEnabled = false
                    it.isChecked = false
                }
            }
            notificationsSwitchEnabled = isChecked
            recalculateButtonEnabledState()
        }
        binding.newProductsAndOffersNotifications.setOnCheckedChangeListener { _, isChecked ->
            newProductsAndOffersNotificationsEnabled = isChecked
            recalculateButtonEnabledState()
        }
        binding.authorizationNotifications.setOnCheckedChangeListener { _, isChecked ->
            authorizationNotificationsEnabled = isChecked
            recalculateButtonEnabledState()
        }
        val profileName = binding.profileName
        profileName.editText?.addTextChangedListener {
            val length = it?.length ?: 0
            nameFieldValid = length > 0 && length <= profileName.counterMaxLength
            recalculateButtonEnabledState()
        }
        binding.userPhone.editText?.addTextChangedListener {
            phoneFieldValid = it?.isNotEmpty() ?: false
            recalculateButtonEnabledState()
        }
        binding.genders.setOnCheckedChangeListener { _, checkedId ->
            validGenderSelected = checkedId != -1
            recalculateButtonEnabledState()
        }
        val recordWasSaved =
            Snackbar.make(binding.root, R.string.record_saved, Snackbar.LENGTH_LONG)
        saveButton?.setOnClickListener {
            recordWasSaved.show()
        }
    }

    private fun recalculateButtonEnabledState() {
        val notificationStateValid =
            !notificationsSwitchEnabled || authorizationNotificationsEnabled || newProductsAndOffersNotificationsEnabled
        saveButton?.isEnabled =
            validGenderSelected && nameFieldValid && phoneFieldValid && notificationStateValid
    }
}