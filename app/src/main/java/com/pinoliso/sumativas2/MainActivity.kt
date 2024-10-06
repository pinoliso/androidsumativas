package com.pinoliso.sumativas2

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

var loggedUser = User(name = "", email = "", password = "")

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        textToSpeech = TextToSpeech(this, this)
        setContent {
            MyApp(textToSpeech)
        }
    }

    private fun speakText(text: String) {
        // Convertir el texto a audio
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Establecer el idioma de TextToSpeech
            val result = textToSpeech.setLanguage(java.util.Locale("es", "ES"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Manejar el error si el idioma no está soportado
                println("El idioma seleccionado no está soportado.")
            }
        } else {
            // Manejar el error de inicialización
            println("Inicialización de TextToSpeech fallida.")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }

}

@Composable
fun MyApp(textToSpeech: TextToSpeech) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController = navController) }
        composable("register") { RegisterScreen(navController = navController) }
        composable("session") { SessionScreen(navController = navController) }
        composable("recovery") { RecoveryScreen(navController = navController) }
        composable("signs") { SignsScreen(navController = navController, textToSpeech) }
        composable("audio") { AudioScreen(navController = navController, textToSpeech) }
    }
}
