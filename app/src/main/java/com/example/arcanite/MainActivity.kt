package com.example.arcanite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.arcanite.navigation.Navigation
import com.example.arcanite.navigation.Screens
import com.example.arcanite.ui.theme.ArcaniteTheme
import com.example.uikit.helpfulFunctions.ChangeNavigationBarColor
import com.example.uikit.helpfulFunctions.ChangeStatusBarColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArcaniteTheme {
                ChangeNavigationBarColor(color = MaterialTheme.colorScheme.background)
                ChangeStatusBarColor(color = MaterialTheme.colorScheme.background)
                val navigationController = rememberNavController()
                Navigation(
                    navigationController = navigationController,
                    startDestination = Screens.Splash.screen,
                )
            }
        }
    }
}
