package com.example.evops.core.presentation.style

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.evops.R

private val OpenSans =
    FontFamily(
        Font(R.font.open_sans_light, FontWeight.Light),
        Font(R.font.open_sans, FontWeight.Normal),
        Font(R.font.open_sans_medium, FontWeight.Medium),
        Font(R.font.open_sans_semibold, FontWeight.SemiBold),
        Font(R.font.open_sans_bold, FontWeight.Bold),
        Font(R.font.open_sans_extrabold, FontWeight.ExtraBold),
    )

val Typography =
    Typography(
        displayMedium =
            TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.15.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.25.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
            ),
    )
