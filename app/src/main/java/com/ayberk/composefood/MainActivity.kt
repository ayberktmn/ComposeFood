package com.ayberk.composefood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayberk.composefood.entity.Yemekler
import com.ayberk.composefood.ui.theme.ComposeFoodTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background){
                    SayfaGecisleri()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(navController = navController)
        }
        composable(
            route = "anasayfa/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            Anasayfa(navController)
        }
        composable("detay_sayfa/{yemek}", arguments = listOf(
            navArgument("yemek"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("yemek")
            val yemek = Gson().fromJson(json, Yemekler::class.java)
            DetaySayfa(yemek = yemek, navController)
        }
         composable("siparis_sayfa/{yemekadi}", arguments = listOf(
            navArgument("yemekadi"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("yemekadi")
            val yemekadi = Gson().fromJson(json, Yemekler::class.java)
            SiparisSayfa(yemekadi = yemekadi.yemek_adi,navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFoodTheme {

    }
}
