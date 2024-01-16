package com.example.my.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.my.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val formaDJKFontFamily = FontFamily(
    Font(R.font.formadjrmicro_black,FontWeight.Black),
    Font(R.font.formadjrmicro_bold,FontWeight.Bold),
    Font(R.font.formadjrmicro_extrabold,FontWeight.ExtraBold),
    Font(R.font.formadjrmicro_light,FontWeight.Light),
    Font(R.font.formadjrmicro_medium,FontWeight.Medium),
    Font(R.font.formadjrmicro_extralight,FontWeight.ExtraLight),
    Font(R.font.formadjrmicro_regular,FontWeight.Normal)
)
val navigoFontFamily = FontFamily(
    Font(R.font.navigo_black,FontWeight.Black),
    Font(R.font.navigo_bold,FontWeight.Bold),
    Font(R.font.navigo_extralight,FontWeight.ExtraLight),
    Font(R.font.navigo_light,FontWeight.Light),
    Font(R.font.navigo_medium,FontWeight.Medium),
    Font(R.font.navigo_regular,FontWeight.Normal),
    Font(R.font.navigo_thin,FontWeight.Thin)
)

val ralewayFontFamily= FontFamily(
    Font(R.font.raleway_regular,FontWeight.Normal)
)
