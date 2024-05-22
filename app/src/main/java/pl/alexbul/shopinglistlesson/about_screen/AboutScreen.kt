package pl.alexbul.shopinglistlesson.about_screen

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.alexbul.shopinglistlesson.R
import pl.alexbul.shopinglistlesson.ui.theme.BlueLight


@Composable
fun AboutScreen () {

    val uriHandler = LocalUriHandler.current
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Icon(painterResource(id =  R.drawable.logo), contentDescription = "logo",
            modifier = Modifier.size(140.dp),
            tint = BlueLight)
        Spacer(modifier = Modifier.height(15.dp))
        Text (text = "This APP develop by WILD RACCOON \n"+
        "Version 1.0.1 \n"+
        "To get more information \n",
            textAlign = TextAlign.Center)

Text(modifier = Modifier.padding(top = 10.dp).clickable {
    uriHandler.openUri("https://www.nhl.com")
},
    text = "<<<< CLICK HERE>>>>", color = BlueLight)

    }
}
