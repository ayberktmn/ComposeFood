package com.ayberk.composefood

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Resources.Theme
import android.graphics.drawable.ColorStateListDrawable
import android.os.Bundle
import android.view.WindowInsets.Side
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.ayberk.composefood.ui.theme.ComposeFoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background){
                    Anasayfa()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(){

    val yemekListesi = remember { mutableStateListOf<Yemekler>() }

    LaunchedEffect(key1 = true){

        val y1= Yemekler(1,"Köfte","kofte",55)
        val y2= Yemekler(1,"Ayran","ayran",15)
        val y3= Yemekler(1,"Fanta","fanta",20)
        val y4= Yemekler(1,"Makarna","makarna",40)
        val y5= Yemekler(1,"Kadayıf","kadayif",35)
        val y6= Yemekler(1,"Baklava","baklava",45)

        yemekListesi.add(y1)
        yemekListesi.add(y2)
        yemekListesi.add(y3)
        yemekListesi.add(y4)
        yemekListesi.add(y5)
        yemekListesi.add(y6)


    }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Yemekler")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.anaRenk),
                    titleContentColor = colorResource(id = R.color.white),
                )
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(top = 70.dp)
            ){
                items (
                    count =yemekListesi.count(),
                    itemContent = {
                        val yemek = yemekListesi[it]
                        Card (modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()){
                            Row(modifier = Modifier.clickable {

                            }) {
                             Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                 .padding(all = 10.dp)
                                 .fillMaxWidth()
                             ) {
                                 val activity = (LocalContext.current as Activity)
                                 Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                      yemek.yemek_resim,"drawable",activity.packageName
                                 )),
                                     contentDescription = "", modifier = Modifier.size(100.dp))
                                Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()

                                ){
                                    Column(
                                        verticalArrangement =  Arrangement.SpaceEvenly,
                                        modifier = Modifier.fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Text(text = yemek.yemek_adi, fontSize = 20.sp)
                                        Spacer(modifier = Modifier.size(20.dp))
                                        Text(text = "${yemek.yemek_fiyat} ₺", color = Color.Blue)
                                    }
                                    Icon(painter = painterResource(id = R.drawable.arrow_resim),
                                         contentDescription ="" )
                                }
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFoodTheme {
        Anasayfa()
    }
}