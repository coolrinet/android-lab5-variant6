package com.coolrinet.lab5variant6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coolrinet.lab5variant6.databinding.ActivityResultBinding

private const val EXTRA_TOTAL_COST =
    "com.coolrinet.lab5variant6.total_cost"

private const val TOTAL_COST_KEY =
    "TOTAL_COST_KEY"

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private var totalCost = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        totalCost = intent.getDoubleExtra(EXTRA_TOTAL_COST, 0.0)

        binding.apply {
            totalCostTextView.text = getString(R.string.total_cost, totalCost)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(TOTAL_COST_KEY, totalCost)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        totalCost = savedInstanceState.getDouble(TOTAL_COST_KEY)
    }

    companion object {
        fun newIntent(packageContext: Context, totalCost: Double): Intent {
            return Intent(packageContext, ResultActivity::class.java).apply {
                putExtra(EXTRA_TOTAL_COST, totalCost)
            }
        }
    }
}