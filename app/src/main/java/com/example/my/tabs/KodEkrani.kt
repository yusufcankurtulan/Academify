package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KodEkrani(navController: NavHostController) {
    var kod by remember { mutableStateOf(Array(4) { "" }) }
    var showErrorSnackbar by remember { mutableStateOf(false) }
    var isImageClicked by remember { mutableStateOf(false) }


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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .aspectRatio(1f)
                        .clickable {
                            isImageClicked = !isImageClicked
                            // Image tıklandığında NavController üzerinden popBackStack() çağrısı yapılır
                            navController.popBackStack()
                        },
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(57.dp))


            val annotatedText = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = koyu,
                        fontFamily = navigoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                ) {
                    append("E-posta Adresinizine  \n\n")
                    append("Gönderilen Kodu Girin")

                }
            }

            Text(
                text = annotatedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle
                            (fontSize = 16.sp,
                            fontFamily = formaDJKFontFamily,
                            fontWeight = FontWeight.Light)) {
                        append("Lütfen E-Postanıza gelen dijital kodu yazınız")
                    }
                },
                textAlign = TextAlign.Center,
                color = koyu,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            )  {
                val textFieldCount = 4
                for (i in 0 until textFieldCount) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .weight(1f)
                            .fillMaxWidth()
                            .background(koyu, RoundedCornerShape(16.dp)) // Kenarları yuvarlatılmış şekilde arka planı oluşturur
                    ) {
                        OutlinedTextField(
                            value = kod[i],
                            onValueChange = { yeniDeger ->
                                if (yeniDeger.length <= 1) {
                                    val guncellenmisKod = kod.clone()
                                    guncellenmisKod[i] = yeniDeger
                                    kod = guncellenmisKod
                                }
                            },
                            textStyle = TextStyle(
                                fontFamily = formaDJKFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent), // Dış kutuyu yuvarlatmak için arka plan rengini şeffaf yapar
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color.White,
                                focusedBorderColor = Color.White, // Odaklanılmış durum için kenar rengi
                                unfocusedBorderColor = Color.White

                            ),
                            shape = RoundedCornerShape(16.dp) // İçteki text field'ın kenarlarını yuvarlar
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(88.dp))

            // "Tamam" Butonu
            Button(onClick = {
                // Girilen kodun doğruluğunu kontrol et
                val girilenKod = kod.joinToString("")
                if (girilenKod == "1234") { // "1234" doğru kodla değiştirilmelidir
                    navController.navigate(Routes.YeniSifreEkrani.route) // Kod doğruysa bir sonraki ekrana geç
                } else {
                    showErrorSnackbar = true // Kod yanlışsa Snackbar göster
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 109.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors( sarı) // Renk ayarı burada yapılıyor
            ) {
                Text("Gönder",
                    textAlign = TextAlign.Center,
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = koyu,
                    fontSize = 16.sp)
            }


            // Snackbar
            if (showErrorSnackbar) {
                Snackbar(
                    modifier = Modifier.padding(6.dp),
                    action = {
                        TextButton(onClick = { showErrorSnackbar = false }) {

                        }
                    },
                ) {
                    Text("Yanlış kod. Lütfen tekrar deneyin.",
                        textAlign = TextAlign.Center )
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun KodEkraniPreview() {
    // Using a dummy NavController for preview purposes
    val navController = rememberNavController()
    KodEkrani(navController)
}



