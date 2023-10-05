package com.hfad.temperatureconverter

import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfad.temperatureconverter.ui.theme.TemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MainActivityContent()
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(){
    val celsius = remember { mutableIntStateOf(0) }
    val newCelsius = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()){
        Header(R.drawable.sunrise, "sunrise image" )
        EnterTemperature(newCelsius.value){newCelsius.value = it }
        Row(modifier =  Modifier.fillMaxWidth().padding(0.dp, 16.dp,0.dp, 16.dp ),
            horizontalArrangement = Arrangement.Start) {
            ConvertButton {
                // tam thoi vi du bam vao nut Convert thi doi gia tri
                // bien celsius sang tu 0 ban dau sang 30, vi celsius la mutableIntStateOf
                // nen se bao cho TemperatureText thay doi text hien thi nhiet do
                // tu 0 ban dau sang 30
                //celsius.value = 30
                newCelsius.value.toIntOrNull()?.let {
                    celsius.value = it
                }
            }
        }
        TemperatureText(celsius.value)
    }

}
@Composable
fun ConvertButton(clicked : () -> Unit) {
    Button(onClick = clicked) {
        Text("Convert")
    }
}

@Composable
fun Header(image: Int, description: String) {
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TemperatureText(celsius: Int) {
    val fahrenheit = (celsius.toDouble()*9/5)+32
    Text("$celsius Celsius is $fahrenheit Fahrenheit")
}

@Composable
fun EnterTemperature(temperature : String, changed: (String) -> Unit) {
    TextField(
        value = temperature,
        label = {Text("Enter a temperature")},
        onValueChange = changed,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainActivityContent()
}