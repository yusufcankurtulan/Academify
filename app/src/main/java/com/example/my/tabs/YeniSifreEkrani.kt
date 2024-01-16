package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YeniSifreEkrani(navController: NavHostController) {
    var yeniSifre by remember { mutableStateOf("") }
    var yeniSifreTekrar by remember { mutableStateOf("") }
    var sifreGorunur1 by remember { mutableStateOf(false) }
    var sifreGorunur2 by remember { mutableStateOf(false) }

    val beyaz = Color(0xFFFFFFFF)

    var showErrorSnackbar by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.sifre),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val annotatedText = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = koyu,
                        fontFamily = navigoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                ) {
                    append("Yeni Şifre \n")


                }
            }
            Spacer(modifier = Modifier.height(105.dp))

            Text(
                text = annotatedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(215.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle
                            (
                            fontSize = 16.sp,
                            fontFamily = formaDJKFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append("Yeni şifreniz eskisinden farklı olmalıdır.")
                    }
                },
                textAlign = TextAlign.Center,
                color = koyu,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(28.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 20.dp)
                    .background(koyu, RoundedCornerShape(16.dp))
            ) {
                // Yeni Şifre Girişi
                OutlinedTextField(
                    value = yeniSifre,
                    onValueChange = { yeniSifre = it },
                    visualTransformation = if (sifreGorunur1) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        val image =
                            if (sifreGorunur1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (sifreGorunur1) "Şifreyi Gizle" else "Şifreyi Göster"
                        IconButton(onClick = { sifreGorunur1 = !sifreGorunur1 }) {
                            Icon(imageVector = image, description, tint = beyaz,modifier = Modifier.size(20.dp))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    textStyle = TextStyle(
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White, // İçindeki metin rengini beyaz yapmak için
                        focusedBorderColor = koyu, // Odaklandığında çizgi rengi
                        unfocusedBorderColor = koyu
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 20.dp)
                    .background(koyu, RoundedCornerShape(16.dp))
            ) {
                // Yeni Şifre Tekrar Girişi
                OutlinedTextField(
                    value = yeniSifreTekrar,
                    onValueChange = { yeniSifreTekrar = it },
                    visualTransformation = if (sifreGorunur2) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        val image =
                            if (sifreGorunur2) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (sifreGorunur2) "Şifreyi Gizle" else "Şifreyi Göster"
                        IconButton(onClick = { sifreGorunur2 = !sifreGorunur2 }) {
                            Icon(imageVector = image, description, tint = beyaz,modifier = Modifier.size(20.dp))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    textStyle = TextStyle(
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedBorderColor = koyu,
                        unfocusedBorderColor = koyu
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))


            Button(
                onClick = {
                    if (yeniSifre == yeniSifreTekrar && yeniSifre.isNotBlank()) {
                        // Yeni şifre kaydedilebilir
                        // Örneğin: Şifreyi bir veritabanına kaydetme işlemi gerçekleştirilebilir
                        navController.navigate(Routes.Giris.route)
                    } else {
                        showErrorSnackbar = true

                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 109.dp),
                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(sarı)
            )

            {
                Text("Gönder",
                    textAlign = TextAlign.Center,
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = koyu,
                    fontSize = 16.sp)
            }

            if (showErrorSnackbar) {
                Snackbar(
                    modifier = Modifier
                        .padding(6.dp),
                    action = {
                        TextButton(onClick = { showErrorSnackbar = false }) {

                        }
                    },
                ) {
                    Text(
                        "Şifreler eşleşmiyor tekrar deneyiniz.",
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}
@Preview
@Composable
fun PreviewYeniSifreEkrani() {
    // Varsayılan olarak bir NavHostController oluşturulabilir veya null gönderilebilir
    val navController = rememberNavController()
    YeniSifreEkrani(navController)
}