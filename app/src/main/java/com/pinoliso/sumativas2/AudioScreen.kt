package com.pinoliso.sumativas2

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbsUpDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioScreen(navController: NavController, textToSpeech: TextToSpeech) {

    val items = listOf(
        IconItem("Pararse", Icons.Default.Accessibility),
        IconItem("Escuchar", Icons.Default.Hearing),
        IconItem("Mirar", Icons.Default.Face),
        IconItem("Saludar", Icons.Default.EmojiPeople),
        IconItem("Saludar 2", Icons.Default.Handshake),
        IconItem("Parar", Icons.Default.PanTool),
        IconItem("Evaluar", Icons.Default.ThumbsUpDown),
        IconItem("Leer", Icons.Default.Search)
    )
    var word by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDao = UserDao()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Audio",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("session")}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Volver"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        innerPadding.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF48A1AF))
                .padding(20.dp, 85.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text("Ingresar Palabra") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                value = word,
                onValueChange = { word = it },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = setOnClickListener@{

                    if(word.isBlank()) {
                        Toast.makeText(context, "Ingrese un texto", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
                    word = ""

                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF333333),
                    contentColor = Color(0xFFECECEC)
                ), modifier = Modifier.height(55.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Escuchar",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(fontSize = 25.sp)
                )
            }
        }

    }
}

@Preview
@Composable
fun AudioScreenPreview() {
    val textToSpeech = null
    textToSpeech?.let { SignsScreen(navController = rememberNavController(), it) }
}

