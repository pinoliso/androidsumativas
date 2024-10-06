package com.pinoliso.sumativas2

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test
import android.speech.tts.TextToSpeech
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import java.util.Locale

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun LoginScreenTests() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController = navController)
                }
            }
        }

        composeTestRule.onNodeWithText("Lets Talk").assertExists()
        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
        composeTestRule.onNodeWithText("Recuperar Clave").assertExists()
        composeTestRule.onNodeWithText("Registrarse").assertExists()
    }

    @Test
    fun RegisterScreenTests() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController = navController)
                }
                composable("register") {
                    RegisterScreen(navController = navController)
                }
            }
        }

        composeTestRule.onNodeWithText("Registrarse").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Nombre").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Registrarse").assertExists()
    }

    @Test
    fun RecoveryScreenTests() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController = navController)
                }
                composable("recovery") {
                    RecoveryScreen(navController = navController)
                }
            }
        }

        composeTestRule.onNodeWithText("Recuperar Clave").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Recuperar Contraseña").assertExists()
    }

    @Test
    fun SessionScreenTests() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "session") {
                composable("session") {
                    SessionScreen(navController = navController)
                }
            }
        }

        composeTestRule.onNodeWithText("Señas").assertExists()
        composeTestRule.onNodeWithText("Audios").assertExists()
    }

    @Test
    fun SignsScreenTests() {

        var textToSpeech: TextToSpeech? = null
        val context: Context = ApplicationProvider.getApplicationContext()
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.setLanguage(java.util.Locale("es", "ES"))
            }
        }

        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "signs") {
                composable("signs") {
                    SignsScreen(navController = navController, textToSpeech = textToSpeech!!)
                }
            }
        }

        composeTestRule.onNodeWithText("Pararse").assertExists()
        composeTestRule.onNodeWithText("Escuchar").assertExists()
        composeTestRule.onNodeWithText("Mirar").assertExists()
        composeTestRule.onNodeWithText("Saludar").assertExists()
        composeTestRule.onNodeWithText("Saludar 2").assertExists()
        composeTestRule.onNodeWithText("Parar").assertExists()
        composeTestRule.onNodeWithText("Evaluar").assertExists()
        composeTestRule.onNodeWithText("Leer").assertExists()

    }

    @Test
    fun AudioScreenTests() {

        var textToSpeech: TextToSpeech? = null
        val context: Context = ApplicationProvider.getApplicationContext()
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.setLanguage(java.util.Locale("es", "ES"))
            }
        }

        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "audio") {
                composable("audio") {
                    AudioScreen(navController = navController, textToSpeech = textToSpeech!!)
                }
            }
        }

        composeTestRule.onNodeWithText("Ingresar Palabra").assertExists()
        composeTestRule.onNodeWithText("Escuchar").assertExists()

    }

}