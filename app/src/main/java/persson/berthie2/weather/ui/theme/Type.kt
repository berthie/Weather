package persson.berthie2.weather.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import persson.berthie2.weather.R

// Set of Material typography styles to start with

val dankFonts = FontFamily(
    Font(R.font.dankmono_regular, weight = FontWeight.Normal),
    Font(R.font.dankmono_bold, weight = FontWeight.Bold),
    Font(R.font.dankmono_italic, weight = FontWeight.Medium, style = FontStyle.Italic),

)


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = dankFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = dankFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    caption = TextStyle(
        fontFamily = dankFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)