package com.netflix.clone

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.netflix.clone.di.appModule
import com.netflix.clone.navigation.NetflixCloneNavHost
import com.netflix.clone.presentation.components.NetflixNavigationBar
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle =
                SystemBarStyle.auto(
                    Color.TRANSPARENT,
                    Color.TRANSPARENT,
                    detectDarkMode = { true },
                ),
        )
        setContent {
            val navController = rememberNavController()

            NetflixCloneTheme {
                KoinApplication(application = { modules(appModule) }) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NetflixNavigationBar(navController)
                        },
                        containerColor = ExtendedTheme.colors.neutralBlack,
                        contentColor = ExtendedTheme.colors.neutralWhite,
                        contentWindowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal),
                    ) { innerPadding ->
                        NetflixCloneNavHost(
                            navController = navController,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                                    .consumeWindowInsets(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
