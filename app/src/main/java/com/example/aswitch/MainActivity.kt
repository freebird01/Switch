package com.example.aswitch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aswitch.ui.theme.SwitchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwitchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwitchExample(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SwitchExample(name: String, modifier: Modifier = Modifier) {
    val myText = remember {
        mutableStateOf("This image is visible")
    }

    val myButtonState = remember{
        mutableStateOf(false)
    }

    val imageAlpha = remember {
        mutableFloatStateOf(1.0f)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
    ) {
        Switch(
            checked = myButtonState.value,
            onCheckedChange = {
                myButtonState.value = it
                if (it) {
                    imageAlpha.value = 0.0f
                    myText.value = "This image is invisible"
                }
                else {
                    imageAlpha.value = 1.0f
                    myText.value = "This image is visible"
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                uncheckedThumbColor = Color.Red,
                checkedTrackColor = Color.Red,
                uncheckedTrackColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.size(50.dp))

        Image(
            painter = painterResource(R.drawable.image_1),
            contentDescription = "",
            alpha = imageAlpha.value,
            modifier = Modifier.width(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.size(50.dp))

        Text(
            text = myText.value,
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
                .width(250.dp)
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwitchTheme {
        SwitchExample("Android")
    }
}