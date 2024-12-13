package com.fatihbilgin.odev3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatihbilgin.odev3.ui.theme.BackgroundColor
import com.fatihbilgin.odev3.ui.theme.BackgroundColorDark
import com.fatihbilgin.odev3.ui.theme.Odev3Theme
import com.fatihbilgin.odev3.ui.theme.TextColorDark
import com.fatihbilgin.odev3.ui.theme.TextColorWhite
import com.fatihbilgin.odev3.ui.theme.TopBarColor
import com.fatihbilgin.odev3.ui.theme.TopBarColorDark
import com.fatihbilgin.odev3.ui.theme.poppins


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Odev3Theme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(darkTheme: Boolean = isSystemInDarkTheme()) {
    val configuration = LocalConfiguration.current
    val ekranYuksekligi = configuration.screenHeightDp
    val ekranGenisligi = configuration.screenWidthDp

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                MyText(
                    textResId = R.string.topBarBaslik,
                    fontSize = ((ekranGenisligi + ekranYuksekligi) / 60).sp,
                    color = if (darkTheme) TextColorDark else TextColorWhite,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if (darkTheme) TopBarColorDark else TopBarColor,
                titleContentColor = if (darkTheme) TextColorDark else TextColorWhite,
            )
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(if (darkTheme) BackgroundColorDark else BackgroundColor),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyText(
                textResId = R.string.sehirYazi,
                fontSize = (ekranGenisligi / 15).sp,
                fontWeight = FontWeight.Bold,
                color = if (darkTheme) TextColorDark else TextColorWhite,
            )

            Image(
                painter = painterResource(id = R.drawable.hava),
                contentDescription = null,
                modifier = Modifier.size((ekranGenisligi / 2.5).dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = (ekranGenisligi / 5).dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MyText(
                    textResId = R.string.dereceYazi,
                    fontSize = (ekranGenisligi / 10).sp,
                    fontWeight = FontWeight.Bold,
                    color = if (darkTheme) TextColorDark else TextColorWhite,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width((ekranGenisligi / 50).dp))
                Column(
                    modifier = Modifier.align(Alignment.Bottom),
                    horizontalAlignment = Alignment.Start
                ) {
                    MyText(
                        textResId = R.string.havaDurumuYazi,
                        fontSize = (ekranGenisligi / 25).sp,
                        fontWeight = FontWeight.Bold,
                        color = if (darkTheme) TextColorDark else TextColorWhite,
                    )
                }
            }

            MyText(
                textResId = R.string.tarihYazi,
                fontSize = (ekranGenisligi / 20).sp,
                fontWeight = FontWeight.Bold,
                color = if (darkTheme) TextColorDark else TextColorWhite,
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                WeatherRow(
                    textResId = R.string.bugünYazi,
                    degreeResId = R.string.bugünDereceYazi,
                    iconPainter = painterResource(id = R.drawable.hava),
                    ekranGenisligi = ekranGenisligi,
                    textColor = if (darkTheme) TextColorDark else TextColorWhite,
                )
                Spacer(modifier = Modifier.height((ekranYuksekligi / 50).dp))
                WeatherRow(
                    textResId = R.string.yarinYazi,
                    degreeResId = R.string.yarinDereceYazi,
                    iconPainter = painterResource(id = R.drawable.hava2),
                    ekranGenisligi = ekranGenisligi,
                    textColor = if (darkTheme) TextColorDark else TextColorWhite,
                )
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .height((ekranYuksekligi / 15).dp)
                    .width((ekranGenisligi / 2).dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (darkTheme) TopBarColorDark else TopBarColor,
                    contentColor = if (darkTheme) TextColorDark else TextColorWhite,
                )
            ) {
                MyText(textResId = R.string.lokasyonDegistirYazi, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun MyText(
    @StringRes textResId: Int? = null,
    text: String? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null
) {
    val resolvedText = textResId?.let { stringResource(id = it) } ?: text.orEmpty()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.width((4).dp))
        }
        Text(
            text = resolvedText,
            fontFamily = poppins,
            fontSize = fontSize,
            color = color,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun WeatherRow(
    @StringRes textResId: Int,
    @StringRes degreeResId: Int,
    iconPainter: Painter,
    ekranGenisligi: Int,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = (ekranGenisligi / 20).dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyText(
            textResId = textResId,
            fontSize = (ekranGenisligi / 25).sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            icon = {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size((ekranGenisligi / 15).dp)
                )
            }
        )
        MyText(
            textResId = degreeResId,
            fontSize = (ekranGenisligi / 25).sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Odev3Theme {

    }
}