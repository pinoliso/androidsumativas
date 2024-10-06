package com.pinoliso.sumativas2

import android.content.Context
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
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun LoginScreen(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDao = UserDao()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF48A1AF))
            .padding(30.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.manos),
            contentDescription = "PockerMania",
            modifier = Modifier.scale(5.0f).height(120.dp)
        )

        Text(
            text = "Lets Talk",
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFECECEC)
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Outlined.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = setOnClickListener@{

            val db = Firebase.firestore

            if(email.isBlank()) {
                Toast.makeText(context, "Ingrese un Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(password.isBlank()) {
                Toast.makeText(context, "Ingrese un Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Ingrese un Email válido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            userDao.validateLogin(email, password) { success, user, message ->
                if (success) {
                    if (user != null) {
                        loggedUser = user
                    }
                    navController.navigate("session")
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }

        },colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF333333),
            contentColor = Color(0xFFECECEC)
        ),modifier = Modifier.height(55.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(fontSize = 25.sp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                navController.navigate("recovery")
            }) {
                Text(
                    text = "Recuperar Clave",
                    color = Color(0xFFECECEC)
                )
            }

            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text(
                    text = "Registrarse",
                    color = Color(0xFFECECEC)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}