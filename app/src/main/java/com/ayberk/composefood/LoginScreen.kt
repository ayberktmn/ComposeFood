package com.ayberk.composefood

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController : NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isShowingEmailError by remember { mutableStateOf(false) }
    var isShowingPasswordError by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.burger), // Correct the resource loading
                    contentDescription = null, // You can add a description if needed
                    modifier = Modifier
                        .size(200.dp) // Adjust the size as needed
                        .padding(16.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text("E-posta") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 8.dp),
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Şifre") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        isEmailValid = isValidEmail(email) // Email kontrolü burada yapılıyor
                        isPasswordValid = isValidPassword(password)
                        if (isEmailValid && isPasswordValid) {
                           // navController.navigate("anasayfa")
                            navController.navigate("anasayfa/$email")
                        } else {
                            isShowingEmailError = true
                            isShowingPasswordError = true
                        }
                    },
                    enabled = email.isNotBlank() && password.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(bottom = 16.dp)
                ) {
                    Text("Giriş Yap")
                }
                if (!isEmailValid && isShowingEmailError) {
                    Text(
                        text = "Lütfen geçerli bir e-posta adresi giriniz.",
                        color = Color.Red,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                else if (!isPasswordValid && isShowingPasswordError) {
                    Text(
                        text = "Şifre uzunluğu 6 karakterden fazla olmalıdır.",
                        color = Color.Red,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

            }
}




fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isValidPassword(password: CharSequence): Boolean {
    return password.length >= 6 // Şifrenin en az 6 karakter olmasını isteyelim.
}
