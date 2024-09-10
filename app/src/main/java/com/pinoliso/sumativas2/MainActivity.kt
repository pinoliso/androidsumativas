package com.pinoliso.sumativas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val users = mutableListOf<User>()
var loggedUser = User(name = "", email = "", password = "")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            users.add(User(name = "Gloria Alvarez", email = "test1@test.com", password = "1234"))
            users.add(User(name = "Gonzalo Huerta", email = "test2@test.com", password = "1234"))
            users.add(User(name = "Jimena Rincón", email = "test3@test.com", password = "1234"))
            users.add(User(name = "Rodrigo Tupp", email = "test4@test.com", password = "1234"))
            users.add(User(name = "Carlos Goma", email = "test5@test.com", password = "1234"))
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("register") { RegisterScreen(navController = navController) }
        composable("session") { SessionScreen(navController = navController) }
        composable("recovery") { RecoveryScreen(navController = navController) }
        composable("signs") { SignsScreen(navController = navController) }
        composable("audio") { AudioScreen(navController = navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
