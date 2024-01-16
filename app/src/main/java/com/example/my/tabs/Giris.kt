package com.example.my.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.my.Routes
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.my.R
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.my.springboot.AuthDBaseViewModel
import com.example.my.springboot.Users
import com.example.my.ui.theme.formaDJKFontFamily
import com.example.my.ui.theme.gri
import com.example.my.ui.theme.gricik
import com.example.my.ui.theme.koyu
import com.example.my.ui.theme.navigoFontFamily
import com.example.my.ui.theme.sarı
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GirisEkrani(navController: NavHostController,authDBaseViewModel: AuthDBaseViewModel) {
    val scope = authDBaseViewModel.viewModelScope
    var hatirla by remember { mutableStateOf(false) }
    var sifreGorunur1 by remember { mutableStateOf(false) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val sifre = remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                .padding(16.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Oturum Aç",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 5.dp),
                    color = koyu,
                    fontSize = 32.sp,
                    fontFamily = navigoFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(198.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        color = gricik,
                        shape = RoundedCornerShape(16.dp) // Yuvarlatılmış kenarlar
                    )

            ) {
                Icon(
                    imageVector = Icons.Filled.Mail,
                    contentDescription = null,
                    tint = gri,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(30.dp)
                )


                fun isValidEmail(email: String): Boolean {
                    val emailRegex = "^[A-Za-z](.*)(@std.yeditepe.edu.tr)\$"
                    return email.matches(emailRegex.toRegex())
                }
                TextField(
                    value = email.value,
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
                        .fillMaxWidth()
                        .padding(end = 11.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = gri,
                        focusedBorderColor = gricik,
                        unfocusedBorderColor = gricik,
                        cursorColor = gri),
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(
                        color = gricik,
                        shape = RoundedCornerShape(16.dp) // Yuvarlatılmış kenarlar
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = gri,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(30.dp)
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
                    singleLine = true, // Tek satırlı olmasını sağlar
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = gri,
                        focusedBorderColor = gricik,
                        unfocusedBorderColor = gricik,
                        cursorColor = gri),
                    trailingIcon = {
                        val image =
                            if (sifreGorunur1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description =
                            if (sifreGorunur1) "Şifreyi Gizle" else "Şifreyi Göster"
                        IconButton(onClick = { sifreGorunur1 = !sifreGorunur1 }) {
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = gri
                            )
                        }
                    }
                )
            }

            // "Beni Hatırla" Seçeneği ve Şifremi Unuttum Butonu aynı satırda

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Beni Hatırla",
                        fontFamily = formaDJKFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = koyu // Metin rengi
                    )
                    Checkbox(
                        checked = hatirla,
                        onCheckedChange = { hatirla = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = koyu, // Seçili durumda olan renk
                            uncheckedColor = koyu // Seçili olmayan durumda olan renk
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ClickableText(
                        text = AnnotatedString("Şifremi Unuttum"),
                        onClick = { navController.navigate(Routes.KayitSifremEkrani.route) },
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = formaDJKFontFamily,
                            fontWeight = FontWeight.Light,
                            color = koyu
                        ),
                        modifier = Modifier
                            .padding(top = 15.dp, end = 20.dp)
                    )
                }
            }


            Button(
                onClick = {
                    // Burada kayıt işlemini gerçekleştiricez sonra
                    //val context = LocalContext.current
                    //Toast.makeText(context, "Kayıt işlemi başarıyla tamamlandı", Toast.LENGTH_SHORT).show()
                    //navController.navigate(Routes.Mail.route)
                    val users = Users(
                        ad = "",
                        soyad = "",
                        email = email.value.text,
                        sifre = sifre.value.text,
                        faculty = "",
                        department = ""
                    )
                    scope.launch {
                        authDBaseViewModel.login(users = users) { loginSuccessful, message ->
                            if (loginSuccessful) {
                                navController.navigate(Routes.Anasayfa.route)
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    koyu,
                    contentColor = Color.White // Buton metin rengi
                ),

                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    "Oturum Aç",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Hesabın yok mu? ",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
                Text(
                    "Hesap Oluştur",
                    fontFamily = formaDJKFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.Kayit.route)
                    }
                )
            }
            Spacer(modifier = Modifier.height(148.dp))


        }
    }
}
/*
@Preview(showSystemUi = true)
@Composable
fun Screen(){
    val navController = rememberNavController()
    val authDBaseViewModel = AuthDBaseViewModel()
    GirisEkrani(navController, authDBaseViewModel  )
}*/