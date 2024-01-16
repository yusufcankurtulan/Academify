package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.my.R
import com.example.my.Routes
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my.springboot.AuthApiService
import com.example.my.springboot.AuthDBaseViewModel
import com.example.my.springboot.Users
import com.example.my.ui.theme.acıkgri
import com.example.my.ui.theme.acıkmavi
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.grimsi
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KayitEkrani(navController: NavHostController, authDBaseViewModel: AuthDBaseViewModel) {
    val scope = authDBaseViewModel.viewModelScope

    val faculties = listOf("Bilgisayar ve Bilişim Bilimleri Fakültesi", "İletişim Fakültesi")
    val departments = mapOf(
        "Bilgisayar ve Bilişim Bilimleri Fakültesi" to listOf(
            "Bilgi Güvenliği Teknolojisi Bölümü", "Bilişim Sistemleri Ve Teknolojileri Bölümü",
            "Yazılım Geliştirme Bölümü", "Yönetim Bilişim Sistemleri Bölümü"
        ),
        "İletişim Fakültesi" to listOf(
            "Gazeticilik Bölümü",
            "Görsel İletişim Tasarımı Bölümü",
            "Radyo Televizyon ve Sinema Bölümü",
            "Reklam Tasarımı ve İletişim bölümü",
            "Halkla İlişkiler ve Tanıtım Bölümü"
        )
    )


    val ad = remember { mutableStateOf(TextFieldValue()) }
    val soyad = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val sifre = remember { mutableStateOf(TextFieldValue()) }
    var sifreGorunur1 by remember { mutableStateOf(false) }
    val sifre2 = remember { mutableStateOf(TextFieldValue()) }
    var sifreGorunur2 by remember { mutableStateOf(false) }
    var selectedFaculty by remember { mutableStateOf<String?>(null) }
    var selectedDepartment by remember { mutableStateOf<String?>(null) }
    val beyaz = Color(0xFFFFFFFF)
    var kabul by remember { mutableStateOf(false) }


    val focusManager = LocalFocusManager.current

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
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    "Hesap Oluştur",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 5.dp),
                    color = koyu,
                    fontFamily = navigoFontFamily,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(75.dp))


            TextField(
                value = ad.value,
                onValueChange = { ad.value = it },
                label = { Text("Ad", color = koyu) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()

                    .background(beyaz),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = koyu,
                    unfocusedBorderColor = koyu,
                    cursorColor = koyu),
            )

            TextField(
                value = soyad.value,
                onValueChange = { soyad.value = it },
                label = { Text("Soyad", color = koyu) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()

                    .background(beyaz),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = koyu,
                    unfocusedBorderColor = koyu,
                    cursorColor = koyu),
            )


            // E-posta geçerliliğini ve uzantı kontrolu
            fun isValidEmail(email: String): Boolean {
                val emailRegex = "^[A-Za-z](.*)(@std.yeditepe.edu.tr)\$"
                return email.matches(emailRegex.toRegex())
            }
            TextField(
                value = email.value,
                label = { Text("E-mail", color = koyu) },
                onValueChange = {
                    email.value = it

                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = !isValidEmail(email.value.text),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = koyu,
                    unfocusedBorderColor = koyu,
                    cursorColor = koyu),
            )

            TextField(
                value = sifre.value,
                onValueChange = { sifre.value = it },
                visualTransformation = if (sifreGorunur1) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(beyaz),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = koyu,
                    unfocusedBorderColor = koyu,
                    cursorColor = koyu),
                label = { Text("Şifre", color = koyu) },
                trailingIcon = {
                    val image =
                        if (sifreGorunur1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (sifreGorunur1) "Şifreyi Gizle" else "Şifreyi Göster"
                    IconButton(onClick = { sifreGorunur1 = !sifreGorunur1 }) {
                        Icon(imageVector = image, contentDescription = description, tint = gri)
                    }
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Şifreniz en az bir özel karakter içermeli ve en az 8 karakter olmalıdır.",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 5.dp),
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    fontSize = 9.sp
                )
            }

            TextField(
                value = sifre2.value,
                onValueChange = { sifre2.value = it },
                visualTransformation = if (sifreGorunur2) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(beyaz),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = koyu,
                    unfocusedBorderColor = koyu,
                    cursorColor = koyu),
                label = { Text("Şifre Tekrar", color = koyu) },
                trailingIcon = {
                    val image =
                        if (sifreGorunur2) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (sifreGorunur2) "Şifreyi Gizle" else "Şifreyi Göster"
                    IconButton(onClick = { sifreGorunur2 = !sifreGorunur2 }) {
                        Icon(imageVector = image, contentDescription = description, tint = gri)
                    }
                }
            )



            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                var isFacultyExpanded by remember { mutableStateOf(false) }

                // Fakulte Dropdown
                TextField(
                    value = selectedFaculty ?: "",
                    onValueChange = { newText ->
                        selectedFaculty = newText
                        selectedDepartment = null
                    },
                    label = { Text("Fakülte", color = koyu) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(beyaz),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = koyu,
                        unfocusedBorderColor = koyu,
                        cursorColor = koyu),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    trailingIcon = {
                        IconButton(onClick = { isFacultyExpanded = true }) {
                            Icon(Icons.Filled.ArrowDropDown, "İşaret")
                        }
                    },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            isFacultyExpanded = true
                            // Move focus to department dropdown on pressing Enter
                            if (selectedFaculty?.isNotBlank() == true) {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        }
                    )
                )

                DropdownMenu(
                    expanded = isFacultyExpanded,
                    onDismissRequest = { isFacultyExpanded = false },
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(360.dp)
                ) {
                    faculties.forEach { faculty ->
                        DropdownMenuItem(onClick = {
                            selectedFaculty = faculty
                            isFacultyExpanded = false
                        } ,
                            faculty
                        )
                    }
                }
            }


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                var isDepartmentExpanded by remember { mutableStateOf(false) }

                TextField(
                    value = selectedDepartment ?: "",
                    onValueChange = { newText -> selectedDepartment = newText },
                    label = { Text("Bölüm", color = koyu) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(beyaz),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = koyu,
                        unfocusedBorderColor = koyu,
                        cursorColor = koyu),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    trailingIcon = {
                        IconButton(onClick = { isDepartmentExpanded = true }) {
                            Icon(Icons.Filled.ArrowDropDown, "İşaret")
                        }
                    }
                )

                DropdownMenu(
                    expanded = isDepartmentExpanded,
                    onDismissRequest = { isDepartmentExpanded = false },
                    modifier = Modifier
                        .background(acıkgri)
                        .width(360.dp)
                ) {
                    departments[selectedFaculty]?.forEach { department ->
                        DropdownMenuItem(onClick = {
                            selectedDepartment = department
                            isDepartmentExpanded = false
                        }, department)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {

                Text(
                    "Kişisel verilerime ilişkin Aydınlatma Metni’ni okudum ve onaylıyorum.",
                    fontSize = 9.sp,
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .padding(end = 28.dp)
                        .padding(top = 8.dp)
                )

                Checkbox(
                    checked = kabul,
                    onCheckedChange = { kabul = it },

                    colors = CheckboxDefaults.colors(
                        checkedColor = koyu,
                        uncheckedColor = koyu
                    )
                )

            }
            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // Divider yüksekliği
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Kayıt Ol Butonu
            Button(
                onClick = {
                    // Burada kayıt işlemini gerçekleştiricez sonra
                    //val context = LocalContext.current
                    //Toast.makeText(context, "Kayıt işlemi başarıyla tamamlandı", Toast.LENGTH_SHORT).show()
                    //navController.navigate(Routes.Mail.route)
                    val users = Users(ad = ad.value.text, soyad = soyad.value.text, email = email.value.text ,sifre = sifre.value.text, faculty = "", department = "")
                    scope.launch {
                        authDBaseViewModel.register(users = users) { registerSuccessful, message ->
                            if (registerSuccessful){
                                navController.navigate(Routes.Giris.route)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    koyu,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text("Hesap Oluştur",fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }
            Row(
                modifier = Modifier.padding(top = 2.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Hesabın var mı? ",  fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp)
                Text(
                    "Oturum aç",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Giris.route)
                    }
                )
            }
        }
    }
}

@Composable
fun DropdownMenuItem(onClick: () -> Unit, faculty: String) {
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(56.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart

    ) {
        Text(faculty,
                color = gri )
    }
    Divider(
        color = grimsi, // Çizgi rengi
        thickness = 1.dp, // Çizgi kalınlığı
        modifier = Modifier.padding(horizontal = 20.dp) // Çizgi kenar boşlukları
    )
}

/*
@Preview
@Composable
fun KayitEkraniPreview() {
    val navController = rememberNavController()
    KayitEkrani(navController = navController)
}
*/






