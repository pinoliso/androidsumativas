package com.pinoliso.sumativas2

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RecoveryScreen(navController: NavController) {

}

@Preview
@Composable
fun RecoveryScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}