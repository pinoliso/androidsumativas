package com.pinoliso.sumativas2

import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class IconItem(val text: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignsScreen(navController: NavController) {


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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Señas",
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items) { item ->
                    IconCard(item)
                }
            }
        }

    }
}

@Preview
@Composable
fun SignsScreenPreview() {
    SignsScreen(navController = rememberNavController())
}

@Composable
fun IconCard(item: IconItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "${item.text} Icono",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.text,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111111)
                ),
                modifier = Modifier.padding(1.dp),
            )
        }
    }
}