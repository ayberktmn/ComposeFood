package com.ayberk.composefood

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayberk.composefood.Viewmodel.AnasayfaViewModel
import com.ayberk.composefood.Viewmodel.AnasayfaViewModelFactory
import com.google.gson.Gson

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController : NavController){
    val context = LocalContext.current
    val viewModel: AnasayfaViewModel = viewModel(
        factory = AnasayfaViewModelFactory(context.applicationContext as Application)
    )
    val yemekListesi = viewModel.yemeklerListesi.observeAsState(listOf())

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Yemekler") },
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
                    count =yemekListesi.value!!.count(),
                    itemContent = {
                        val yemek = yemekListesi.value!![it]
                        Card (modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()){
                            Row(modifier = Modifier.clickable {
                                val yemekJson = Gson().toJson(yemek)
                                navController.navigate("detay_sayfa/$yemekJson")
                            }) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxWidth()
                                ) {
                                    val activity = (LocalContext.current as Activity)
                                    Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                        yemek.yemek_resim_adi,"drawable",activity.packageName
                                    )),
                                        contentDescription = "", modifier = Modifier.size(100.dp))
                                    Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()

                                    ){
                                        Column(
                                            verticalArrangement =  Arrangement.SpaceEvenly,
                                            modifier = Modifier
                                                .fillMaxHeight()
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


