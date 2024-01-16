package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.ui.theme.acıkmavi
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı
import androidx.compose.runtime.setValue
import com.example.my.Routes

@Composable
fun FavoriEkrani(navController: NavHostController) {
    val favoriDersler = FavoriManager.getFavoriDersler()

    var isImageClicked by remember { mutableStateOf(false) }


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
                text = "Favoriler",
                modifier = Modifier.padding(bottom = 8.dp)
                    .padding(top = 6.dp)
                    .padding(start = 12.dp),
                color = koyu,
                fontFamily = navigoFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favoriDersler.chunked(2)) { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    pair.forEach { ders ->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable {
                                    navController.navigate("${Routes.DersDetay.route}/$ders") // DersDetay sayfasına git
                                }
                                .background(color = acıkmavi, shape = RoundedCornerShape(16.dp))
                        ) {
                            Text(
                                text = ders,
                                fontFamily = formaDJKFontFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                                    .padding(8.dp),
                                color = beyaz
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun FavoriDersItem(ders: String, navController: NavHostController) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .background(color = Color.Gray)
                .padding(8.dp)

        ) {
            Text(
                text = ders,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}


object FavoriManager {
    private val favoriDersler = mutableSetOf<String>()

    // Dersi favoriye ekle
    fun addFavoriDers(dersKodu: String) {
        favoriDersler.add(dersKodu)
        // Burada favori dersler listesini tercihler veya veritabanı kullanarak güncelleyebilirsiniz
    }

    // Dersi favoriden kaldır
    fun removeFavoriDers(dersKodu: String) {
        favoriDersler.remove(dersKodu)
        // Burada favori dersler listesini tercihler veya veritabanı kullanarak güncelleyebilirsiniz
    }

    // Ders favori mi kontrolü
    fun isDersFavori(dersKodu: String): Boolean {
        return favoriDersler.contains(dersKodu)
    }

    // Favori dersleri al
    fun getFavoriDersler(): List<String> {
        return favoriDersler.toList()
    }
}


@Preview
@Composable
fun FavoriEkraniPreview() {
    val navController = rememberNavController()
    FavoriEkrani(navController = navController)

}



