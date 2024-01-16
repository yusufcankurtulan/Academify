package com.example.my.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SubdirectoryArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonSearchScreen(navController: NavHostController) {
    var isImageClicked by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf("") }
    val lessons = remember {
        listOf(
            "Acm 431",
            "Acm 365",
            "Acm 369",
            "Comp 301",
            "Comp 303",
            "Vcd 321",
            "Vcd 421",
            "Vcd 471",
            "Vcd 171"
        )
    }

    val filteredLessons = lessons.filter { it.contains(keyword, ignoreCase = true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.oturum),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
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

            Text(
                text = "Ders Arama ",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 65.dp),
                color = koyu,
                fontFamily = navigoFontFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(108.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = koyu,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = keyword,
                        onValueChange = { keyword = it },
                        textStyle = TextStyle(color = Color.White, fontSize = 16.sp,
                            fontFamily = formaDJKFontFamily, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        cursorBrush = SolidColor(Color.White),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        )
                    )

                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(color = sarı, shape = RoundedCornerShape(20.dp))
                            .clickable {
                                // Arama işlemi yapılabilir
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
            LessonList(lessons = filteredLessons,
                navController = navController,
                keyword = keyword)


        }
    }
}


@Composable
fun LessonList(
    lessons: List<String>,
    navController: NavHostController,
    keyword: String,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {
        val filteredLessons =
            if (keyword.isNotEmpty()) lessons.filter { it.contains(keyword, ignoreCase = true) } else lessons
        items(filteredLessons) { lesson ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("${Routes.DersDetay.route}/$lesson")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (keyword.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.SubdirectoryArrowRight,
                        contentDescription = "Arrow Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                Text(
                    text = lesson,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Divider(
                color = Color.Black, // Çizgi rengi
                thickness = 1.dp, // Çizgi kalınlığı
                modifier = Modifier.padding(horizontal = 8.dp) // Çizgi kenar boşlukları
            )
        }
    }
}
