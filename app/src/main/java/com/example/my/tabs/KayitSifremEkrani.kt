package com.example.my.tabs
//Bu sayfa şifremi unuttum kısmı
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KayitSifremEkrani(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
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
                        fontSize = 32.sp)
                ) {
                    append("Şifremi Unuttum")


                }
            }

            Text(
                text = annotatedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(235.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle
                            (fontSize = 16.sp,
                            fontFamily = formaDJKFontFamily,
                            fontWeight = FontWeight.Light)) {
                        append("Doğrulama kodu almak için \n")
                        append("lütfen e-posta adresinizi eksiksiz giriniz")
                    }
                },
                textAlign = TextAlign.Center,
                color = koyu,
                modifier = Modifier.fillMaxWidth()
            )

                // Email Adresi Girişi

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(20.dp)
                    .background(koyu, RoundedCornerShape(16.dp))
                    // Koyu renkte yuvarlatılmış köşeli arka plan
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    textStyle = TextStyle(color = Color.Black,
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedBorderColor = koyu,
                        unfocusedBorderColor = koyu
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
            }

                Spacer(modifier = Modifier.height(78.dp))
                // "Kod Gönder" Butonu
                Button(
                    onClick = { navController.navigate(Routes.KodEkrani.route) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(horizontal = 109.dp),
                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(sarı) // Renk ayarı burada yapılıyor
                ) {
                    Text("Gönder",
                        textAlign = TextAlign.Center,
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = koyu,
                        fontSize = 16.sp)
                }



            }
        }
    }
@Preview
@Composable
fun PreviewKayitSifremEkrani() {
    // Varsayılan olarak bir NavHostController oluşturulabilir veya null gönderilebilir
    val navController = rememberNavController()
    KayitSifremEkrani(navController)
}