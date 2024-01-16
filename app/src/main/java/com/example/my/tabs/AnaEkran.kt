package com.example.my.tabs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my.Routes
import com.example.my.springboot.AuthDBaseViewModel




@Composable
fun AnaEkran(authDBaseViewModel: AuthDBaseViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Routes.Giris.route) {
            GirisEkrani(navController = navController, authDBaseViewModel = authDBaseViewModel)
        }
        composable(Routes.Kayit.route) {
            KayitEkrani(navController = navController, authDBaseViewModel = authDBaseViewModel)
        }
        composable(Routes.KayitSifremEkrani.route) {
            KayitSifremEkrani(navController = navController)
        }
        composable(Routes.YeniSifreEkrani.route) {
            YeniSifreEkrani(navController = navController)
        }
        composable(Routes.Mail.route) {
            Mail(navController = navController)
        }
        composable(Routes.Anasayfa.route) {
            MainScreen(navController = navController)
        }
        composable(Routes.KodEkrani.route) {
            KodEkrani(navController = navController)
        }
        composable(Routes.Ders.route) {
            LessonSearchScreen(navController = navController)
        }
        composable(Routes.Hoca.route) {
            TeacherSearchScreen(navController = navController)
        }
        composable(Routes.Cikis.route) {
            GirisEkrani(navController = navController, authDBaseViewModel = authDBaseViewModel)
        }
        composable(Routes.AnketSayfasi.route) {
            AnketSayfasi(navController = navController)
        }
        composable(Routes.Favori.route) {
            FavoriEkrani(navController = navController)
        }
        composable(Routes.ChatRoom.route) {
            ChatRoomListScreen( navController = navController)
        }
        composable("${Routes.Chat.route}/{chatRoomId}") { backStackEntry ->
            val chatRoomId = backStackEntry.arguments?.getString("chatRoomId") ?: ""

            ChatScreen(chatRoomId = chatRoomId, navController = navController)
        }
        composable(Routes.DersDetay.route + "/{courseName}") { backStackEntry ->
            val courseName = backStackEntry.arguments?.getString("courseName") ?: ""
            val viewModel: FavoriViewModel = viewModel() // ViewModel'i oluştur

            DersDetailScreen(navController, courseName, viewModel)
        }
        composable(Routes.HocaDetay.route + "/{teacher}") { backStackEntry ->
            val teacher = backStackEntry.arguments?.getString("teacher") ?: ""

            HocaDetailScreen(navController, teacher)
        }
    }
}
