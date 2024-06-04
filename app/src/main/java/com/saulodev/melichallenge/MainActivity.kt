package com.saulodev.melichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.saulodev.melichallenge.ui.navigation.AppNavigation
import com.saulodev.melichallenge.ui.theme.MeliChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity class annotated with @AndroidEntryPoint for Hilt integration.
 * Extends ComponentActivity, the base class for activities using Jetpack Compose.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting. Sets up the content view using Jetpack Compose.
     * Initializes the app theme and navigation.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeliChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(rememberNavController())
                }
            }
        }
    }
}
