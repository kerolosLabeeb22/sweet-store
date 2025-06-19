package com.example.online_shope

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.online_shope.Activity.DetailsScreenCont
import com.example.online_shope.Activity.HomeScreenContent
import com.example.online_shope.Activity.SplashScreenContent
import com.example.online_shope.destinaions.DetailsDestination
import com.example.online_shope.destinaions.HomeDestination
import com.example.online_shope.destinaions.RegisterDestination
import com.example.online_shope.destinaions.SplashDestination
import com.example.online_shope.ui.theme.Online_shopeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Online_shopeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navHostController = rememberNavController()

                    NavHost(navController = navHostController, startDestination = SplashDestination){
                        composable<SplashDestination>{
                            SplashScreenContent(navHostController)
                        }
                        composable<HomeDestination>{
                            HomeScreenContent(navHostController){
                            }
                        }
                        composable<RegisterDestination>{

                        }

                        composable<DetailsDestination>{
                            //DetailsScreenCont(navHostController)
                        }

                    }
                }
            }
        }
    }
}

