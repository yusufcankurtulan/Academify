package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import com.example.my.ui.theme.acıkgri
import com.example.my.ui.theme.acıkmavi
import com.example.my.ui.theme.beyaz
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.grimsi
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.mavi
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı

data class ChatRoom(val id: String, val name: String)

@Composable
fun ChatRoomListScreen(navController: NavHostController) {
    var isImageClicked by remember { mutableStateOf(false) }

    val chatRooms = listOf(
        ChatRoom("1", "Genel"),
        ChatRoom("2", "Bilgisayar ve Bilişim Bilimleri Fakültesi"),
        ChatRoom("3", "Yazılım Geliştirme"),
        ChatRoom("4", "Bilgi Güvenliği Teknolojisi"),
        ChatRoom("5", "Bilişim Sistemleri Ve Teknolojileri"),
        ChatRoom("6", "Yönetim Bilişim Sistemleri"),
        ChatRoom("7", "İletişim Fakültesi"),
        ChatRoom("8", "Gazeticilik"),
        ChatRoom("9", "Görsel İletişim Tasarımı"),
        ChatRoom("10", "Radyo Televizyon ve Sinema"),
        ChatRoom("11", "Reklam Tasarımı ve İletişim"),
        ChatRoom("12", "Halkla İlişkiler ve Tanıtım ")

    )
    // Chat odalarının listesi
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(beyaz)
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
                text = "Sohbet Odaları",
                modifier = Modifier.padding(bottom = 8.dp)
                    .padding(top = 6.dp)
                    .padding(start = 12.dp),
                color = koyu,
                fontFamily = navigoFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .background(acıkmavi, RoundedCornerShape(16.dp)),
        ) {
            LazyColumn {
                items(chatRooms.size) { index ->
                    ChatRoomItem(chatRoom = chatRooms[index], navController = navController)
                }
            }
        }
    }
}

@Composable
fun ChatRoomItem(chatRoom: ChatRoom,  navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
            .padding(horizontal = 12.dp)
            .padding(top = 36.dp)
            .padding(bottom = 6.dp)

            .clickable {
                navController.navigate("${Routes.Chat.route}/${chatRoom.id}")
                // Burada tıklanan chat odasına geçiş yapılabilir veya başka bir işlem gerçekleştirilebilir
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.chat),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = chatRoom.name,
            modifier = Modifier
                .padding(start = 12.dp),
            color = beyaz,
            fontFamily = formaDJKFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Divider(
        color = beyaz, // Çizgi rengi
        thickness = 1.dp, // Çizgi kalınlığı
        modifier = Modifier.padding(horizontal = 20.dp)
            .padding(bottom = 6.dp)// Çizgi kenar boşlukları
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewChatRoomListScreen() {
    val navController = rememberNavController()
    ChatRoomListScreen(navController)
}