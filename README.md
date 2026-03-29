# Academify

Bu proje Android için Kotlin + Jetpack Compose kullanılarak geliştirilmiş bir eğitim platformu uygulamasıdır. Kullanıcı kayıt/giriş, ders/hoca verisi, anket, favori listesi ve chat odası gibi katmanları içerir.

## Özellikler
- Retrofit tabanlı Auth (Springboot backend API) ve SharedPreferences ile kullanıcı bilgisi saklama
- Anket formu ve yerel anket fonksiyonları
- Ders/hoca detay sayfaları
- Favori listesi ile ders kaydetme
- Chat odası navigasyonu (Geliştirmeye açık)
- Tam ekran, responsive ve modern Jetpack Compose arayüzü

### Mevcut modüller
- Splash / Karşılama / Kayıt / Giriş / Şifre Kurtarma (Şifre Yenileme) akışı
- Ana sayfa (navigasyon menüsü, profil, çıkış)
- Dersler (listeleme, detay, favorilere ekleme)
- Hocalar (listeleme, detay)
- Anket formu (Evet/Hayır türü sorular)
- Chat ve ChatRoom ekranları
- API istemci ve ViewModel katmanı

## Proje yapısı
- `app/src/main/java/com/example/my/MainActivity.kt`: Uygulama giriş noktası ve `AnaEkran` çağrısı.
- `app/src/main/java/com/example/my/tabs`: Bütün Compose ekranları.
- `app/src/main/java/com/example/my/springboot`: Auth API, ViewModel, SharedPreferences.
- `app/src/main/java/com/example/my/anket`: Anket API, ViewModel, model.
- `app/src/main/java/com/example/my/Routes.kt`: Tüm rota tanımları.
- `app/src/main/res`: UI kaynakları, temalar, resimler.

## Teknoloji yığını
- Kotlin
- Jetpack Compose
- Navigation Compose
- ViewModel + Coroutines
- Retrofit 2 + Gson converter
- SharedPreferences
- Material3

## Bağımlılıklar
(Detaylar `build.gradle.kts` içinde bulunabilir)
- `androidx.core:core-ktx`
- `androidx.lifecycle:lifecycle-viewmodel-ktx`
- `androidx.activity:activity-compose`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- `com.google.firebase:firebase-auth` (isteğe bağlı / kullanılmıyor doğrudan ama kod yapısına uygun)

## Hizmetler & API
### Auth (Springboot)
- `POST /api/user-profiles/register` (kullanıcı bilgileri)
- `POST /api/user-profiles/login` (kullanıcı bilgileri)

`Users` modeli (`app/src/main/java/com/example/my/springboot/Users.kt`):
- `ad`, `soyad`, `email`, `sifre`, `faculty`, `department`

### Anket
- `POST /api/anket-yukle` (yeni anket yükleme)
- `GET /api/anketler` (anket listesini çekme)

Not: `AnketSayfasi` şu anda lokal sabit sorular ile çalışır, API gönderimi `AnketDBaseViewModel` üzerinden yapılabilir.

## Akış
1. `MainActivity` içinde `AuthDBaseViewModel` başlatılır.
2. `AnaEkran` NavHost ile `Routes` yönlendirmesini sağlar.
3. `KayitEkrani` kullanıcı kaydını `authDBaseViewModel.register()` ile API'ye gönderir.
4. `GirisEkrani` kullanıcı girişini `authDBaseViewModel.login()` ile API'ye gönderir.
5. Başarılı giriş sonrası `Anasayfa` gösterilir, kullanıcı adı/soyadı `SharedPreferencesManager` kaydedilir.
6. `AnketSayfasi` soruları ve cevapları özelleştirir; 'Gönder' düğmesi mevcut basit duruma geri dönüştür.

## Kullanım
1. Repoyu klonlayın:
```bash
git clone https://github.com/yusufcankurtulan/Academify.git
cd Academify
```
2. Android Studio ile açın.
3. `app/google-services.json` dosyasını yerleştirin (Firebase varsa bu gereklidir).
4. Gradle senkronize edin ve çalıştırın.

## Geliştirme önerileri
- Anket entegrasyonunu gerçekteki API ile tamamla.
- Chat için gerçek zamanlı backend (Firebase/Socket) ekle.
- Hata ve loading durumlarını UI'da göster (Snackbar/Progress).
- Form validasyonunu güçlendir (şifre karakter gereksinimleri, boş alan kontrolleri).
- Unit/UI testlerini ekle.
- Lokalizasyon (TR/EN) ekle.

## Katkıda bulunma
1. Fork yapınız.
2. Yeni branch açınız (`feature/`, `bugfix/`, ...).
3. Değişiklikleri commit/push yapınız.
4. Pull request açınız.

## Lisans
MIT Lisansı
