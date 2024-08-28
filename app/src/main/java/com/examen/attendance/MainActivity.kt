package com.examen.attendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.examen.attendance.presentation.screen.LoginScreen
import com.examen.attendance.presentation.screen.SplashScreen

import com.examen.attendance.ui.theme.MyJetPackAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetPackAppTheme {
                SplashScreen(navigateToLogin = {
                    // Navigate to login screen here
                    setContent {
                        MyJetPackAppTheme {
                            LoginScreen()
                        }
                    }
                })
            }
        }
    }
}

