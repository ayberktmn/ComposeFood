package com.ayberk.composefood

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayberk.composefood.entity.Yemekler
import com.ayberk.composefood.ui.theme.ComposeFoodTheme
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetaySayfa(yemek: Yemekler){
    var yuklenmeTamamlandi by remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Yemekler")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.anaRenk),
                    titleContentColor = colorResource(id = R.color.white),
                )
            )

        }, content = {
            Column (
              modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val activity = (LocalContext.current as Activity)
                if (!yuklenmeTamamlandi) {
                    // Yükleme işlemi devam ediyor
                    CircularProgressIndicator()
                } else {
                Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                    yemek.yemek_resim_adi,"drawable",activity.packageName
                )),
                    contentDescription = "", modifier = Modifier.size(100.dp))
                Text(text = yemek.yemek_adi, color = Color.Black, fontSize = 50.sp)
                Text(text = "${yemek.yemek_fiyat} ₺", color = Color.Blue, fontSize = 50.sp)
                Button(onClick = {
                    Log.e("Yemek", "${yemek.yemek_adi} Siparişi verildi")
                    Toast.makeText(activity,"${yemek.yemek_adi} Siparişi verildi",Toast.LENGTH_SHORT).show()
                },
                    modifier = Modifier.size(250.dp,50.dp),
                    colors = ButtonDefaults.buttonColors(
                           colorResource(id = R.color.anaRenk)
                      )
                    ) {
                        Text(text = "Sipariş Ver")
                }
            }
        }
        })

    LaunchedEffect(Unit) {
        // Yükleme işlemi burada gerçekleşir
        // Örnek: Yükleme işlemi 3 saniye sürsün
        delay(1000)

        // Yükleme işlemi tamamlandığında göstergenin kapanmasını sağlar
        yuklenmeTamamlandi = true


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeFoodTheme {
        
    }
}