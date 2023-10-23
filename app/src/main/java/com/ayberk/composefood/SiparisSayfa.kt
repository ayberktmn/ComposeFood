package com.ayberk.composefood

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SiparisSayfa(yemekadi: String, navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.order), // Correct the resource loading
            contentDescription = null, // You can add a description if needed
            modifier = Modifier
                .size(200.dp) // Adjust the size as needed
                .padding(16.dp)
        )
        Text(
            text = "Sipariş Verilen Yemek: $yemekadi",
            color = androidx.compose.ui.graphics.Color.Black,
            fontSize = 24.sp
        )
        val rastgeleMetin = "Hazırlanma Süresi: ${Random.nextInt(10, 60)} Dakika."
        Spacer(
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = rastgeleMetin,
            color = androidx.compose.ui.graphics.Color.Green,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

