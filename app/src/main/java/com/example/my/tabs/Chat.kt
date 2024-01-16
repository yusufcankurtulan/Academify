package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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

@Composable
fun ChatScreen(chatRoomId: String,navController: NavHostController) {
    val chatRoomTitle = remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<String>()) }
    var newMessage by remember { mutableStateOf(TextFieldValue()) }
    var isImageClicked by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

    // chatRoomId'ye göre başlığı ayarla
    LaunchedEffect(chatRoomId) {
        val title = getChatRoomTitle(chatRoomId) // chatRoomId'ye bağlı olarak başlığı al
        chatRoomTitle.value = title // Mutable state'i güncelle
    }
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
                text = chatRoomTitle.value,
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
                .background(acıkgri, RoundedCornerShape(16.dp)),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(messages.size) { index ->
                    val messageBackgroundColor = if (index % 2 == 0) Color.Blue else Color.Green
                    ChatMessage(message = messages[index], backgroundColor = messageBackgroundColor)
                }
            }
        }

        // Yeni Mesaj Gönderme Alanı
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .background(grimsi, RoundedCornerShape(32.dp))
            ) {
                BasicTextField(
                    value = newMessage,
                    onValueChange = {
                        newMessage = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    textStyle = TextStyle(
                        color = beyaz,
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    ),
                    singleLine = true,
                    maxLines = 1,
                    cursorBrush = SolidColor(Color.White)
                )
            }

            Button(
                onClick = {
                    if (newMessage.text.isNotEmpty()) {
                        messages = messages + newMessage.text
                        newMessage = TextFieldValue()
                    }
                },
                modifier = Modifier
                    .size(width = 105.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors( sarı)

            ) {
                Text(text = "Gönder",
                    color = koyu,
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ChatMessage(message: String, backgroundColor: Color) {
    Card(
        modifier = Modifier.padding(8.dp),

        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier = Modifier
                .background(acıkmavi,RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.CenterEnd

        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = beyaz,
                fontFamily = formaDJKFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    val navController = rememberNavController()
    ChatScreen(chatRoomId = "1", navController = navController)
}

fun getChatRoomTitle(chatRoomId: String): String {
    return when (chatRoomId) {
        "1" -> "Genel"
        "2" -> "Bilgisayar ve Bilişim Bilimleri Fakültesi"
        "3" -> "Yazılım Geliştirme"
        "4" -> "İletişim Fakültesi"
        "5" -> "Görsel İletişim Tasarımı"
        else -> "Unknown Chat Room"
    }
}