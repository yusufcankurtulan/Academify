package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.acıkmavi
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.mavi
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.ralewayFontFamily


@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.karsilama),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f)) // Boşluk ekleniyor

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle
                            (fontSize = 32.sp,
                            fontFamily = navigoFontFamily,
                            fontWeight = FontWeight.Bold)) {
                        append("  Merhaba !")
                    }
                },
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp)) // İki metin arasına boşluk ekleniyor

            val annotatedText = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.White,
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp)) {
                    append("Kendi akademik alanın hakkında danışabileceğin\n")
                    append("ve Hocalar hakkında bilgi bulabileceğin Academify’a \n")
                    append("Hoş Geldin!")
                }
            }

            Text(
                text = annotatedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(30.dp)) // Metinler ve butonlar arasına boşluk ekleniyor

            Button(
                onClick = { navController.navigate(Routes.Kayit.route) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 80.dp),
                colors = ButtonDefaults.buttonColors(acıkmavi)
            ) {
                Text("Hesap oluştur",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(14.dp)) // Butonlar arasına boşluk ekleniyor

            Button(
                onClick = { navController.navigate(Routes.Giris.route) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 80.dp),
                colors = ButtonDefaults.buttonColors(koyu)
            ) {
                Text("Oturum aç",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(80.dp)) // En alt kısma boşluk ekleniyor
        }
    }
}




@Preview
@Composable
fun WelcomeScreenPreview() {
    // Önizleme için bir Navigation Controller oluşturabilirsiniz, burada varsayılan bir NavController kullandım
    val navController = rememberNavController()

    WelcomeScreen(navController = navController)
}


