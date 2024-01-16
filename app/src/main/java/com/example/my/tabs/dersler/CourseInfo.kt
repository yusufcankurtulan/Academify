package com.example.my.tabs.dersler

data class CourseInfo(
    val code: String,
    val title: String,
    val content: String,
    val noteLink: String,
    val instructors: List<String>
)

object CourseDataProvider {
    fun getCourseInfoForLesson(lesson: String): CourseInfo {
        return when (lesson) {
            "Acm 365" -> CourseInfo(
                "Acm 365",
                "İleri Web Dizayn",
                "Web tasarımında dinamik sayfa hazırlamaya yönelik web tasarımı yazılımlarını ve " +
                        "betikleme dillerini kullanmayı öğrenmek.",
                "hhttps://drive.google.com/drive/folders/1Hvt504uuQ5Ze4mgABhn6nYIUBl88aPrP?usp=drive_link",
                listOf("Hacı Ahmet Yıldırım")
            )
            "Acm 369" -> CourseInfo(
                "Acm 369",
                "İşletim Sistemleri I",
                "Bu derste açık kaynaklı Linux sistemi ve GNU uygulama yazılımı vurgulanacak," +
                        " açık kaynaklı işletim sistemleri ve uygulama yazılımın kurulumu, kullanımı ve bakım " +
                        "uygulamaları tanıtacaktır",
                "https://drive.google.com/drive/folders/1WTDD0WtkYjHO-UjOcOnnu3ykeFFCtWcr?usp=drive_link",
                listOf("Avodis Simon Hacınlıyan", "Ali Cihan Keleş")
            )
            "Acm 431" -> CourseInfo(
                "Acm 431",
                "Mobil Cihazları Programlama",
                "Ders, Android açık kaynak platformunu kullanarak mobil uygulamalar tasarlamak ve" +
                        " oluşturmak içindir. Dersin işlenişi, öğrencinin ana uygulama geliştirme yapı taşları ve bunların " +
                        "birbirleriyle etkileşimi aracılığıyla Android™ için geliştirme felsefesini anlamasına yardımcı olacak " +
                        "ders ve laboratuvar kursunun bir kombinasyonu olacaktır",
                "https://drive.google.com/drive/folders/1_1aUsCVNJyoGuxBFoq3eSVaWItYaawqp?usp=drive_link",
                listOf("Engin Kandıran", "Ali Cihan Keleş")
            )
            "Comp 301" -> CourseInfo(
                "Comp 301",
                "Yazılım Mimarisi ve Araçları",
                "Dersin amacı, öğrencileri yazılım mimarlığı alanına tanıtmak, tasarım becerilerini geliştirmek ve " +
                        "soyut düşünme yeteneğini arttırmaktır. Bu dersi alan öğrenciler fonksiyonel ve fonksiyonel olmayan " +
                        "ihtiyaç analizi yapabilir ve yazılımın geliştirilmesi için gerekli yazılımı tasarlayıp geliştirebilir.",
                "https://drive.google.com/drive/folders/1ezjaTzJQyC22Kx7kaqfkxqXj6mYSvhfH?usp=drive_link",
                listOf("Engin Kandıran")
            )
            "Comp 303" -> CourseInfo(
                "Comp 303",
                "İleri Python Programlama",
                "Bu dersin amacı, programlama dilinin gerçek hayattaki uygulamalarda yaygın olarak kullanıldığı bazı ileri " +
                        "düzey konuları tanıtmaktır. Bu dersin sonunda, soket programlama, veritabanı işlemleri (SQL ve NOSQL), web " +
                        "programlama, veri madenciliği teknikleri uygulamaları ve bazı makine öğrenmesi yöntemleri (bazı noktalarda " +
                        "derin öğrenme) gibi python konularını öğreneceksiniz.",
                "https://drive.google.com/drive/folders/1_1L_x1TLYWFuf4rt0mhg8XIeAIaK6m3T?usp=drive_link",
                listOf("Ali Cihan Keleş")
            )
            "Vcd 321" -> CourseInfo(
                "Vcd 321",
                "Tasarımda Kültürel İmgeler",
                "Ders, tasarım tarihinin önemli örneklerini tanıtarak tasarım problemine, sembollere ve görsellere dayanmaktadır. " +
                        "Kültürel mirasa ve güncel problemlere dayalı imgeler, tarihsel ve sosyal bağlamlarıyla birlikte incelenir. Dersin amacı, " +
                        "öğrencilerin kültür imgelerini tanımaları ve bu imgeleri görsel " +
                        "iletişim aracı olarak kullanmaları için bir temel oluşturmaktır.",
                "https://drive.google.com/drive/folders/1j8tNcDqTYMe2HJsRZ7dNHNYhc9F8SEv3?usp=drive_link",
                listOf("Gül Bakan")
            )
            "Vcd 171" -> CourseInfo(
                "Vcd 171",
                "Tasarımın Temmelleri",
                "Dersin amacı, öğrencilere temel tasarım elemanlarını ve tasarım prensiplerini tanıtmak ve temel uygulamalara giriş yapmalarını sağlamaktır." +
                        " Uygulamalar yapılırken tasarımın kavramsal boyutunun sorgulanması ve güncel örnekler üzerinden değerlendirilmesi amaçlanır.",
                "https://drive.google.com/drive/folders/1MspLOvP_iQ_MRQQiGcAWtHBq_ZkrkqDf?usp=drive_link",
                listOf("İrem Tekin Yücesoy")
            )
            "Vcd 421" -> CourseInfo(
                "Vcd 421",
                "Tasarımda Semiyotik Yaklaşımlar",
                "Bu ders, tasarım olgusunun öncelikli olarak kavramsal olarak ele alınmasını ve " +
                        "bu doğrultuda da tasarıma kuramsal yaklaşımların ileri bir seviyede incelenmesini içerir. " +
                        "Öğrenciler, tasarım ve iletişim ile ilgili ileri düzey bilgilere sahip olduktan sonra bu bilgi " +
                        "ve becerilerini göstergesel üretimlere uygulayarak ifade ederler. Dersler öğrencilere; reklamlar, " +
                        "posterler, pankartlar, simgeler, ikonlar ve belirtisel göstergeler aracılığıyla semiyotik farkındalık " +
                        "kazandırmayı amaçlar ve analiz yapmayı öğretir.",
                "https://drive.google.com/drive/folders/1HMmUAfljpE9sLXTHgpNrDrLyihZGWYc0?usp=drive_link",
                listOf("Neda Üçer")
            )
            "Vcd 471" -> CourseInfo(
                "Vcd 471",
                "Etkileşimli Tasarım Stüdyosu",
                "Bu ders kapsamında öğrenciler etkileşimli tasarımın teorik ve uygulamalı yapısını " +
                        "öğrenir ve projeler aracılığıyla deneyimler. Öğrenciler, kodlama ve güncel uygulamalar ile etkileşimin " +
                        "yaratıcı kullanımlarını keşfeder ve uygularlar.",
                "https://drive.google.com/drive/folders/1_ovyONoBz4rKnpS3jkiTldckwTsWdEw0?usp=drive_link",
                listOf("Merve Çaşkurlu")
            )
            else -> CourseInfo(
                "Ders Kodu",
                "Ders Başlığı",
                "Ders içeriği...",
                "https://example.com/notes/ders-kodu",
                listOf("Hoca Adı")
            )
        }
    }
}