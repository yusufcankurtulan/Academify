package com.example.my.tabs

import androidx.browser.customtabs.CustomTabsIntent
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person

import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.mavi
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.viewinterop.AndroidView
import com.example.my.springboot.SharedPreferencesManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    var selectedItem by remember { mutableStateOf<String?>(null) }
    var isMenuOpen by remember { mutableStateOf(false) }

   fun openWebPageCustomTab(url: String, context: Context) {
       val webpage = Uri.parse(url)
       val customTabsIntent = CustomTabsIntent.Builder().build()
       customTabsIntent.launchUrl(context, webpage)
   }

    Box(modifier = Modifier.fillMaxSize()) {
        // Arka plan resmi
        Image(
            painter = painterResource(id = R.drawable.anasayfa), // Arka plan resminizin kaynağını belirtin
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds // Resmin boyutunu ayarlayın
        )

        // Diğer bileşenler
        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(koyu)
                    .padding(top = 20.dp)// Arka plan rengini burada belirtin
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle
                                        (
                                        fontSize = 32.sp,
                                        fontFamily = navigoFontFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("          Anasayfa")
                                }
                            },
                            textAlign = TextAlign.Center,
                            fontFamily = navigoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = beyaz // Text bileşeninin rengini burada beyaz yapabilirsiniz
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { isMenuOpen = true },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = beyaz,
                                modifier = Modifier.size(44.dp)
                            )
                        }
                    }, colors = TopAppBarDefaults.smallTopAppBarColors(koyu)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            if (isMenuOpen) {
                val (userName, userSurname) = sharedPreferencesManager.getUserDetails()
                SideMenu(userName ?: "Ad", userSurname ?: "Soyad", navController) {
                    isMenuOpen = false
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Merhaba Academify'a Hoşgeldin!",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 5.dp),
                        color = koyu,
                        fontSize = 24.sp,
                        fontFamily = navigoFontFamily,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp
                    )
                }

                Text(
                    "Aradığın tüm bilgiler burada!",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(top = 5.dp),
                    color = koyu,
                    fontSize = 16.sp,
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Light,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(45.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(170.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ders),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { navController.navigate(Routes.Ders.route) }
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(154.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.favori),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { navController.navigate(Routes.Favori.route) }
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }





                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom

                ) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(189.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sohbet),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = beyaz, shape = RoundedCornerShape(6.dp))
                                .clickable { navController.navigate(Routes.ChatRoom.route) }
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(205.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hoca),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = beyaz, shape = RoundedCornerShape(6.dp))
                                .clickable { navController.navigate(Routes.Hoca.route) }
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }
                    Button(
                        onClick = {
                            openWebPageCustomTab("https://yeditepe.edu.tr/tr/akademik/akademik-takvim", context)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(acıkmavi),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text("Akademik Takvim'e git.")
                    }
                }
            }
        }
    }

@Composable
fun SideMenu(userName: String, userSurname: String, navController: NavHostController, onClose: () -> Unit) {
    var selectedItem by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable { onClose() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
                .background(acıkmavi)
                .padding(5.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(44.dp),
                    tint = beyaz,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$userName $userSurname",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = beyaz,
                    fontSize = 16.sp
                )
            }


            Spacer(modifier = Modifier.height(50.dp))



            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(bottom = 8.dp)
                    .clickable {
                        selectedItem = "Çıkış Yap"
                        navController.navigate(Routes.WelcomeScreen.route)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = beyaz
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Çıkış Yap",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = beyaz)
            }



        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController)
}
