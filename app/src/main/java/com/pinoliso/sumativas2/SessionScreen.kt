package com.pinoliso.sumativas2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

data class Opcion(
    val name: String,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(navController: NavController) {

    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val opciones = listOf<Opcion>(
        Opcion(name = "Señas", route = "signs"),
        Opcion(name = "Audio", route = "audio")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Selección de Juegos",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("login")}
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF48A1AF))
                .padding(20.dp, 85.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(8.dp), // Esquinas redondeadas
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0C6E7A), contentColor = Color.White) // Color de fondo gris claro
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar Icon",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(4.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                            .padding(8.dp),
                        tint = Color.White
                    )

                    Text(text = "Hola " + loggedUser.name,
                        fontSize = 16.sp,
                        fontWeight= FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(20.dp))

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            for (opcion in opciones) {
                Button(
                    onClick = {
                        navController.navigate(opcion.route)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333333),
                        contentColor = Color(0xFFECECEC)
                    ), modifier = Modifier.height(55.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = opcion.name,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(fontSize = 25.sp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }

    }
}

@Preview
@Composable
fun SessionScreenPreview() {
    SessionScreen(navController = rememberNavController())
}