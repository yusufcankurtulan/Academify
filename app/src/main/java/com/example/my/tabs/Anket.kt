package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.acıkmavi
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.mavi
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnketSayfasi(navController: NavHostController) {

    val sorular = listOf(
        "Hoca çan uyguluyor mu?",
        "Dersleri genelde proje bazli mi isliyor?",
        "Quiz yapıyor mu?",
        "Cok fazla ödev veriyor mu?",
        "Hoca yoklama konusunda katı mı?",
        "Hocanın ders anlatım şekli anlaşılır mı?",
        "Ders materyalleri yeterli ve güncel mi?",
        "Ders materyallerini paylasiyor mu?",
    )

    val cevaplar = remember { mutableStateOf(Array(sorular.size) { false }) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),

                    ) {
                         // Image'ın başına boşluk ekleniyor
                        Image(
                            painter = painterResource(id = R.drawable.group),
                            contentDescription = "Geri",
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(32.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        // Image ile Text arasına boşluk ekleniyor
                        Text(
                            "Geri Bildirim Anketi",
                            modifier = Modifier.padding(top = 3.dp),
                            color = koyu,
                            fontFamily = navigoFontFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = { },
                //...
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {


            itemsIndexed(sorular) { index, soru ->
                SoruItem(soru = soru, cevaplar = cevaplar, index = index)
                Spacer(Modifier.height(8.dp))
            }

            item {
                Button(
                    onClick = {
                        // Cevapları işle ve bir sonraki ekrana geç
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    .padding(horizontal = 85.dp),
                    colors = ButtonDefaults.buttonColors(sarı)
                ) {
                    Text("Gönder",
                        color = koyu,
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp)
                }
                Spacer(Modifier.height(16.dp))

            }
        }
    }
}


@Composable
fun SoruItem(soru: String, cevaplar: MutableState<Array<Boolean>>, index: Int) {



    val evetSecildi = remember { mutableStateOf(false) }
    val hayirSecildi = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            soru,
            color = koyu,
            fontFamily = navigoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Button(
                onClick = {
                    evetSecildi.value = !evetSecildi.value
                    hayirSecildi.value = false
                    cevaplar.value[index] = evetSecildi.value
                },
                colors = ButtonDefaults.buttonColors(if (evetSecildi.value) sarı else acıkmavi),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                val textColor = if (evetSecildi.value) koyu else beyaz
                Text("Evet",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = textColor)
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    hayirSecildi.value = !hayirSecildi.value
                    evetSecildi.value = false
                    cevaplar.value[index] = !hayirSecildi.value
                },
                colors = ButtonDefaults.buttonColors(if (hayirSecildi.value) sarı else acıkmavi),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                val textColor = if (hayirSecildi.value) koyu else beyaz
                Text("Hayır",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = textColor)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AnketSayfasiPreview() {
    val navController = rememberNavController()
    AnketSayfasi(navController)
}