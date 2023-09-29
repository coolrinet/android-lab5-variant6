package com.coolrinet.lab5variant6

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val CURRENT_GASOLINE_BRAND_KEY = "CURRENT_GASOLINE_BRAND_KEY"
private const val NUMBER_OF_LITERS_KEY = "NUMBER_OF_LITERS_KEY"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val gasolineBrands = listOf(
        GasolineBrand("92", 50.0),
        GasolineBrand("95", 55.0),
        GasolineBrand("97", 57.0)
    )

    private var currentGasolineBrandIndex: Int
        get() = savedStateHandle[CURRENT_GASOLINE_BRAND_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_GASOLINE_BRAND_KEY, value)

    var numberOfLitersStr: String
        get() = savedStateHandle[NUMBER_OF_LITERS_KEY] ?: ""
        set(value) = savedStateHandle.set(NUMBER_OF_LITERS_KEY, value)

    fun changeCurrentGasolineBrand(newIndex: Int) {
        currentGasolineBrandIndex = newIndex
    }

    fun calculateTotalCost(): Double {
        val numberOfLiters = numberOfLitersStr.toDouble()
        val currentGasolineBrandPrice = gasolineBrands[currentGasolineBrandIndex].pricePerLiter
        return numberOfLiters * currentGasolineBrandPrice
    }
}