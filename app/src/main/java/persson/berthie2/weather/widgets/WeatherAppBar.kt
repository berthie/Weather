package persson.berthie2.weather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import persson.berthie2.weather.ui.theme.bgColorEdge
import persson.berthie2.weather.ui.theme.darkerOrange

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddAction: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title,
            color = darkerOrange)
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddAction.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "SearchIcon", tint = darkerOrange)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "MoreVertIcon", tint = darkerOrange)
                }
            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = darkerOrange,
                    modifier = Modifier.clickable { onButtonClicked.invoke() }
                )
            }
        },
        backgroundColor = bgColorEdge,
        elevation = elevation
    )
}
