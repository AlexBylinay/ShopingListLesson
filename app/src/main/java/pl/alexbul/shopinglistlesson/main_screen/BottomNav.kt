package pl.alexbul.shopinglistlesson.main_screen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import pl.alexbul.shopinglistlesson.ui.theme.BlueLight
import pl.alexbul.shopinglistlesson.ui.theme.GrayLight


@Composable
fun BottomNav(
    currentRoute: String?,
    onNavigate:(String)-> Unit
) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.AboutItem,
        BottomNavItem.SettingItem
    )
    BottomNavigation(backgroundColor = Color.White) {
        listItems.forEach { item ->

            BottomNavigationItem(
                selected = currentRoute == item.route, onClick =
                {
                   onNavigate(item.route)
                }, icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = item.title)
                }, selectedContentColor = BlueLight,
                unselectedContentColor = GrayLight, alwaysShowLabel = false

            )
        }

    }


}
