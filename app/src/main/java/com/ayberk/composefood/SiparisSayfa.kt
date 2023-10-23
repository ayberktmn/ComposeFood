package com.ayberk.composefood

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiparisSayfa(yemekadi: String, navController: NavController) {
    var yuklenmeTamamlandi by remember { mutableStateOf(false) }
    var countdownMinutes by remember { mutableStateOf(2) } // Initial countdown duration (in minutes)
    var countdownSeconds by remember { mutableStateOf(0) }
    var siparisHazir by remember { mutableStateOf(false) } // A flag to indicate if the order is ready

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!yuklenmeTamamlandi) {
            // Yükleme işlemi devam ediyor
            CircularProgressIndicator()
        } else {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.order),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Sipariş Verilen Yemek: $yemekadi",
                color = androidx.compose.ui.graphics.Color.Black,
                fontSize = 24.sp
            )

            if (countdownMinutes <= 0 && countdownSeconds <= 0) {
                siparisHazir = true
            }

            val countdownTextStyle = if (siparisHazir) {
                TextStyle(
                    color = Color.Green,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                TextStyle(
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier
                .padding(top = 10.dp)
            )
            // Display the countdown timer in minutes and seconds with the determined TextStyle
            Text(
                text = if (siparisHazir) "Siparişiniz Hazır" else "Hazırlanma Süresi: $countdownMinutes dakika $countdownSeconds saniye.",
                style = countdownTextStyle
            )
        }
        LaunchedEffect(Unit) {
            // Yükleme işlemi burada gerçekleşir
            // Örnek: Yükleme işlemi 1.5 saniye sürsün
            delay(1500)

            // Yükleme işlemi tamamlandığında göstergenin kapanmasını sağlar
            yuklenmeTamamlandi = true

            // Start the countdown timer
            while (countdownMinutes > 0 || countdownSeconds > 0) {
                delay(1000) // Wait for 1 second
                if (countdownSeconds == 0) {
                    countdownMinutes--
                    countdownSeconds = 59
                } else {
                    countdownSeconds--
                }
            }
        }
    }
}


