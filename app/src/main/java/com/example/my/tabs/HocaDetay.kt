package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import com.example.my.tabs.hocalar.HocaDataProvider
import com.example.my.ui.theme.acıkgri
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı


data class QuestionItem(val question: String, val percentage: Float)

// Soruları ve yüzdeleri tanımlayın

@Composable
fun HocaDetailScreen(navController: NavHostController,teacher: String) {
    val hocaInfo = remember { HocaDataProvider.getHocaInfoForTeachers(teacher) }
    var questionItems by remember { mutableStateOf<List<QuestionItem>>(emptyList()) }
    LaunchedEffect(key1 = true) {
        questionItems = listOf(
            QuestionItem("Çan uyguluyor mu?", 0.3f),
            QuestionItem("Proje veriyor mu?", 0.6f),
            QuestionItem("Quiz yapıyor mu?", 0.8f),
            QuestionItem("Ödev veriyor mu?", 0.4f),
            QuestionItem("Yoklama konusunda katı mı?", 0.2f)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            var isImageClicked by remember { mutableStateOf(false) }
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
            // İnstructor Adı
            Text(
                text = hocaInfo.instructorName,
                color = gri,
                fontFamily = formaDJKFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }


        // İnstructor Bilgi
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 18.dp)
                .padding(end = 18.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 26.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.human), // Kullanmak istediğiniz resmin adını belirtin
                    contentDescription = "Person Image",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(58.dp)
                        .clip(CircleShape) // Opsiyonel: resminizi daire şeklinde kesebilirsiniz
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = hocaInfo.instructorInfo,
                        color = gri,
                        fontFamily = formaDJKFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }


            // İnstructor Email
            Text(
                text = "Email:" +hocaInfo.instructorEmail,
                color = gri,
                fontFamily = formaDJKFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                "Ders İşleme Şekli" ,
                fontSize = 16.sp,
                color = koyu, // Metin rengi
                fontFamily = navigoFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Ders İşleyiş Detayları
            Box(
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp))

            ) {
                Text(
                    text = hocaInfo.lessonDetails,
                    color = gri,
                    fontFamily = formaDJKFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(questionItems.size) { index ->
                    PercentageBar2(questionItem = questionItems[index])
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center // Centering horizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.AnketSayfasi.route)
                }
                ,colors = ButtonDefaults.buttonColors(
                    sarı,
                    contentColor = koyu // Buton metin rengi
                ),shape = RoundedCornerShape(16.dp),

                ) {
                Text("Anket ",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,)
            }

        }

    }

}


@Composable
fun PercentageBar2(questionItem: QuestionItem) {
    val backgroundColor = acıkgri// Arkaplan rengi
    val progressBarColor = koyu // Yüzdelik bar rengi - Örnek: Kırmızı

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = questionItem.question,
            fontFamily = navigoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 26.dp, end = 8.dp)
                .height(10.dp)
                .background(backgroundColor, RoundedCornerShape(8.dp)) // Arkaplan rengi ve şekli
        ) {
            val progress = questionItem.percentage
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((progress * 100).dp) // Yüzdeye göre genişlik
                    .background(progressBarColor, RoundedCornerShape(8.dp)) // Yüzdelik bar rengi ve şekli
            )
        }

        Text(
            text = "${(questionItem.percentage * 100).toInt()}%",
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}
