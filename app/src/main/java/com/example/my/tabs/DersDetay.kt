package com.example.my.tabs

import androidx.browser.customtabs.CustomTabsIntent
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes

import com.example.my.tabs.dersler.CourseDataProvider
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

@Composable
fun DersDetailScreen(navController: NavHostController, courseName: String, viewModel: FavoriViewModel) {
    val courseInfo = remember { CourseDataProvider.getCourseInfoForLesson(courseName) }
    val favoriCourses = viewModel.favoriCourses.value
    var isStarred by remember { mutableStateOf(favoriCourses.contains(courseName)) }
    val context = LocalContext.current

    var isImageClicked by remember { mutableStateOf(false) }

    fun openWebPageCustomTab(url: String, context: Context) {
        val webpage = Uri.parse(url)
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, webpage)
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
            horizontalArrangement = Arrangement.SpaceBetween // Öğeler arasındaki boşluğu kullanarak hizalama sağlar
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
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

                Text(
                    text = courseInfo.code,
                    color = koyu,
                    fontFamily = navigoFontFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            IconButton(
                onClick = {
                    val isCourseFavorited = viewModel.favoriCourses.value.contains(courseName)
                    viewModel.toggleFavoriCourse(courseName) // Favori durumunu güncelle

                    if (isCourseFavorited) {
                        FavoriManager.removeFavoriDers(courseInfo.code)
                    } else {
                        FavoriManager.addFavoriDers(courseInfo.code)
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                val iconTint = if (viewModel.favoriCourses.value.contains(courseName)) sarı else gri
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Yıldız İkonu",
                    tint = iconTint
                )
            }

        }






        Text(
            text = courseInfo.title,
            color = gri,
            fontFamily = formaDJKFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = courseInfo.content,
            color = gri,
            fontFamily = formaDJKFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = "Not: ${courseInfo.noteLink}",
            color = gri,
            fontFamily = formaDJKFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.clickable {
                openWebPageCustomTab(courseInfo.noteLink, context) // Custom Tab ile URL'yi aç
            }
        )

        Text(
            text = "Eğitmenler:",
            fontFamily = formaDJKFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = gri
        )

        courseInfo.instructors.forEach { instructor ->
            Text(
                text = "- $instructor",
                fontFamily = formaDJKFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable {
                    navigateToInstructorProfile(navController, instructor)
                }
            )
        }
    }
}


fun navigateToInstructorProfile(navController: NavHostController, teacher: String) {
    when (teacher) {
        "Engin Kandıran" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Ali Cihan Keleş" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Hacı Ahmet Yıldırım" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Avodis Simon Hacınlıyan" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Gül Bakan" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Neda Üçer" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Merve Çaşkurlu" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        "Tuna Oktay" -> navController.navigate("${Routes.HocaDetay.route}/$teacher")
        else -> {
            /* Diğer durumlar için gerekli işlemleri yapabilirsiniz */
        }
    }
}

