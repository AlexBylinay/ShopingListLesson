package pl.alexbul.shopinglistlesson.settings_screen

import androidx.compose.ui.graphics.Color

object ColorUtils {
    val colorList = listOf(
        "#9C300F",
        "#A28C86",
        "#9A8D19",
        "#DDD58E",
        "#2C5E86",
        "#3A434A",
        "#033E06",
        "#8DC390",
        "#7D62AE",
        "#3A434A",
        "#9A7584",
        )

    fun getProgressColor ( progress: Float): Color{
        return when (progress){
            in 0.0..0.339 -> Color.Red
            in 0.34..0.669 -> Color.Yellow
            in 0.67..1.0 -> Color.Green

            else -> {Color.Green}
        }

    }
}