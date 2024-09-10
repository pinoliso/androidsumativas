package com.pinoliso.sumativas2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryScreen(navController: NavController) {

    var email by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Volver",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("login")}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                .padding(30.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                label = { Text("Email") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = setOnClickListener@{

                    if(email.isBlank()) {
                        Toast.makeText(context, "Ingrese un Email", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "Ingrese un Email válido", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    val user = users.find { it.email.equals(email) }
                    if (user != null) {
                        Toast.makeText(context, "El correo no está registrado", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    Toast.makeText(context, "Se ha enviado un correo con la recuperación", Toast.LENGTH_LONG).show()

                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF333333),
                    contentColor = Color(0xFFECECEC)
                ), modifier = Modifier.height(55.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Recuperar Contraseña",
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
fun RecoveryScreenPreview() {
    RecoveryScreen(navController = rememberNavController())
}