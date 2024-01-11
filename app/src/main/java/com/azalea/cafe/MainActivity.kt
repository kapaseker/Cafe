package com.azalea.cafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.loopon.cafe.Cafe
//import cn.loopon.cafe.CafeProvider
import cn.loopon.cafe.CafeTheme
import cn.loopon.cafe.DUSK
import cn.loopon.cafe.MAIN

//import com.azalea.cafe.ui.theme.CafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        Greeting("Android")
                        Box(modifier = Modifier
                            .size(200.dp)
                            .background(colorResource(id = Cafe.color.purple_200)))
                        Text(text = "${Cafe.color.purple_200}")
                        Image(painter = painterResource(id = Cafe.mipmap.sky), contentDescription = "")
                        Text(text = "Cup: ${Cafe.getCup()::class.java}")
                        var cupCheck by remember {
                            mutableStateOf(false)
                        }
                        if (cupCheck) {
                            Cafe.setCup(DUSK)
                        } else {
                            Cafe.setCup(MAIN)
                        }
                        Switch(checked = cupCheck, onCheckedChange = {
                            cupCheck = it
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier, color = colorResource(id = Cafe.color.purple_200)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    CafeTheme {
//        Greeting("Android")
//    }
}