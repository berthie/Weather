package persson.berthie2.weather.screens.searchscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import persson.berthie2.weather.navigation.WeatherScreens
import persson.berthie2.weather.ui.theme.bgColorCenter
import persson.berthie2.weather.ui.theme.bgColorEdge
import persson.berthie2.weather.ui.theme.darkerOrange
import persson.berthie2.weather.ui.theme.lightOrange
import persson.berthie2.weather.widgets.WeatherAppBar

@Composable
fun WeatherSearchScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,

            ) {
            navController.popBackStack()
        }
    }) {
        Box(Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(listOf(bgColorCenter, bgColorEdge)))) {
            Surface() {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(bgColorEdge)
                ) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(CenterHorizontally)
                    ) { mCity ->
                        navController.navigate(route = WeatherScreens.MainScreen.name + "/$mCity")
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column() {
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Helsingborg",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            }
        )
    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = darkerOrange,
            focusedBorderColor = darkerOrange,
            unfocusedBorderColor = lightOrange,
            unfocusedLabelColor = lightOrange,
            focusedLabelColor = darkerOrange,
            cursorColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)

    )
}
