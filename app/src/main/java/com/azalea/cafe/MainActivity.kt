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
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azalea.cafe.cafe.Cafe
import com.azalea.cafe.cafe.CafeTheme
import com.azalea.cafe.cafe.DUSK
import com.azalea.cafe.cafe.MAIN
import com.azalea.cafe.cafe.NIGHT
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

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
                        Text(text = "Cup: ${Cafe.getCup()::class.java.simpleName}")
                        var cupCheck by remember {
                            mutableIntStateOf(0)
                        }
                        LaunchedEffect(Unit) {
                            snapshotFlow { cupCheck }.distinctUntilChanged().collectLatest {
                                Cafe.setCup(when (cupCheck % 3) {
                                    0 -> {
                                        MAIN
                                    }

                                    1 -> {
                                        DUSK
                                    }

                                    else -> {
                                        NIGHT
                                    }
                                })
                            }
                        }

                        Button(onClick = {
                            cupCheck++
                        }) {
                            Text("Switch")
                        }
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