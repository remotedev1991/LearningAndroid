package com.nak.learningandroid.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nak.learningandroid.ui.theme.LearningAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Box, Column, Row, Card

@SuppressLint("UnrememberedMutableState")
@Composable
fun Calculator(modifier: Modifier = Modifier) {

    var firstInput = remember { mutableStateOf("") } //state creation

    var secondInput = remember { mutableStateOf("") } //state creation

    val currentAction = remember { mutableStateOf("") }

    val output = remember { mutableStateOf("") }

    val withoutRemember = mutableStateOf("")


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    currentAction.value = "Multiply"
                }) {
                    Text("Multiply")
                }
                Button(onClick = {
                    currentAction.value = "Divide"
                }) {
                    Text("Divide")
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    currentAction.value = "Add"
                }) {
                    Text("Add")
                }
                Button(onClick = {
                    currentAction.value = "Substraction"
                }) {
                    Text("Substraction")
                }
            }

            //recomposition
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = firstInput.value,
                onValueChange = { string ->
                    firstInput.value = string
                },
                label = {
                    Text("Enter Value")
                }
            )

            Spacer(Modifier.height(20.dp))
            //recomposition
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = secondInput.value,
                onValueChange = { string ->
                    secondInput.value = string
                },
                label = {
                    Text("Enter Value")
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                text = "Output: ${output.value}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Blue
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onClick = {

                    withoutRemember.value = "Without"

                    val a = firstInput.value.toInt()
                    val b = secondInput.value.toInt()

                    when (currentAction.value) {
                        "Add" -> {
                            output.value = (a + b).toString()
                        }

                        "Substraction" -> {
                            output.value = (a - b).toString()
                        }

                        "Multiply" -> {
                            output.value = (a * b).toString()
                        }

                        "Divide" -> {
                            output.value = (a / b).toString()
                        }
                    }
                }
            ) {
                Text("Calculate  ${withoutRemember.value}")
            }
        }

    }
}


@Preview
@Composable
fun CalculatorPreview() {
    LearningAndroidTheme {
        Calculator()
    }
}