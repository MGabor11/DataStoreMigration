package com.example.datastoremigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoremigration.ui.theme.DataStoreMigrationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DataStoreMigrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val userName by mainViewModel.savedUserName.collectAsState()

                    MainContent(userName) {
                        mainViewModel.saveUserName(it)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(userName: String, onNameSave: (String) -> Unit) {
    val userNameTextState = remember { mutableStateOf(TextFieldValue(userName)) }

    Column {
        Text(
            text = "Hello $userName!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            fontSize = (24.sp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userNameTextState.value,
            onValueChange = { userNameTextState.value = it },
            textStyle = TextStyle(
                fontSize = (16.sp),
                color = Color.DarkGray
            ),
            label = { Text("User name") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(onClick = {
            onNameSave.invoke(userNameTextState.value.text)
        }) {
            Text("Save the username")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("The textfield has this text: " + userNameTextState.value.text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStoreMigrationTheme {
        MainContent("Android") {}
    }
}
