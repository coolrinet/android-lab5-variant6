package com.coolrinet.lab5variant6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.coolrinet.lab5variant6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            numberOfLitersEditText.doOnTextChanged { text, _, _, _ ->
                mainViewModel.numberOfLitersStr = text.toString()
            }

            gasolineBrandRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                val newGasolineBrandIndex = when (checkedId) {
                    gasolineBrand92Radio.id -> 0
                    gasolineBrand95Radio.id -> 1
                    gasolineBrand98Radio.id -> 2
                    else -> 0
                }
                mainViewModel.changeCurrentGasolineBrand(newGasolineBrandIndex)
            }

            calculateCostButton.setOnClickListener {
                if (mainViewModel.numberOfLitersStr.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.numbers_of_liters_not_specified_toast,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                if (gasolineBrandRadioGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.gasoline_brand_not_selected_toast,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val totalCost = mainViewModel.calculateTotalCost()
                val intent = ResultActivity.newIntent(this@MainActivity, totalCost)
                startActivity(intent)
            }
        }
    }
}