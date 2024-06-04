package com.saulodev.melichallenge.utils

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {
    private val locale: Locale = Locale("es", "AR")
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(locale)

    /**
     * Formats the given double value as a currency string in Argentine Pesos.
     *
     * @param amount The amount to be formatted.
     * @return A string representing the formatted currency.
     */
    fun format(amount: Double): String {
        return currencyFormat.format(amount)
    }
}

/**
 * Extension function to format a Double as Argentine Pesos currency string.
 *
 * @return A string representing the formatted currency.
 */
fun Double.formatCurrency(): String {
    return CurrencyFormatter.format(this)
}
