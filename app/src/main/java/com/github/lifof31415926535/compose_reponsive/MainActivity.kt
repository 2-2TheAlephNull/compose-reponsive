package com.github.lifof31415926535.compose_reponsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.lifof31415926535.compose_reponsive.ui.theme.ComposereponsiveTheme
import com.github.lifof31415926535.responsive.Responsive
import com.github.lifof31415926535.responsive.WindowSizeClassesProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposereponsiveTheme {
                WindowSizeClassesProvider {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Responsive(
            compactContent = { Text("The window is Compact.") },
            mediumContent = { Text("The window is Medium") },
            expandedContent = { Text("The window is Expanded") })
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposereponsiveTheme {
        Greeting("Android")
    }
}