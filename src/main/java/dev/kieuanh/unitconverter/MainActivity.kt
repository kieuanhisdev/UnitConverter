package dev.kieuanh.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.kieuanh.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface( // Dùng Surface để bao phủ toàn bộ giao diện
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                             UnitConverter()
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun UnitConverter(



){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("M") }
    var outputUnit by remember { mutableStateOf("M") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iConversionFactor by remember { mutableStateOf(1.0) }
    var oConversionFactor by remember { mutableStateOf(1.0) }

    var context = LocalContext.current

    fun converUnit(){
        var numberInput = inputValue.toDoubleOrNull() ?: 0.0
        outputValue = (numberInput*iConversionFactor/oConversionFactor).toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                converUnit()
                },

            label = {Text("Enter value")}
            )
//        TextField(value = "kiuenah", onValueChange = {})
//        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Box input
            Box{
                //Button input
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false} ){
                    DropdownMenuItem(
                        text = {Text("cm")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Cm"
                            iConversionFactor = 0.01
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Dm")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Dm"
                            iConversionFactor = 0.1
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("M")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "M"
                            iConversionFactor = 1.0
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Km")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Km"
                            iConversionFactor = 1000.0
                            converUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box{
                //output button
                Button(onClick = {oExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false} ){
                    DropdownMenuItem(
                        text = {Text("Cm")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Cm"
                            oConversionFactor = 0.01
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Dm")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Dm"
                            oConversionFactor = 0.1
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("M")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "M"
                            oConversionFactor = 1.0
                            converUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Km")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Km"
                            oConversionFactor = 1000.0
                            converUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}