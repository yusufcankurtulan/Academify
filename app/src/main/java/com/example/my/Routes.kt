package com.example.my

sealed class Routes(val route: String) {
    object SplashScreen : Routes("SplashScreen")
    object WelcomeScreen : Routes("WelcomeScreen")
    object Giris : Routes("Giris")
    object Kayit : Routes("Kayit")
    object KayitSifremEkrani : Routes("KayitSifremEkrani")
    object YeniSifreEkrani : Routes("YeniSifreEkrani")
    object Mail : Routes("Mail")
    object Ders : Routes("Ders")
    object Hoca : Routes("Hoca")
    object Anasayfa : Routes("Ana Sayfa")
    object KodEkrani : Routes("KodEkrani")
    object Cikis: Routes("Cikis")
    object AnketSayfasi : Routes("AnketSayfasi")
    object Favori: Routes("Favori")
    object Chat: Routes("Chat")
    object DersDetay: Routes("DersDetay")
    object HocaDetay: Routes("HocaDetay")
    object ChatRoom: Routes("ChatRoom")
}