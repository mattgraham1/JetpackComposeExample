package com.graham4.mycomposeapplication

import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.layout.Size
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppContent()
            }
        }
    }
}

@Composable
private fun AppContent() {
    Crossfade(ScreenStatus.currentScreen) { screen ->
        Surface(color = (+MaterialTheme.colors()).background) {
            when (screen) {
                is Screen.Home -> Greeting(name = "Android")
                is Screen.Details -> CardDetails()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column() {
        TopAppBar(
            title = { Text(text = "My Compose App") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_baseline_menu_24) {
                    Log.d("MATT", "Button Clicked")
                }
            }
        )
        VerticalScroller() {
            Column() {
                Card()
                Text("Hello $name!", Spacing(left = 16.dp, right = 16.dp))
                Text("Text1", Spacing(left = 16.dp, right = 16.dp))
                Row(modifier = Spacing(left = 16.dp, right = 16.dp)) {
                    Text("Row1")
                    Text("Row2")
                }
                Card()
                Card()
            }
        }
    }
}

@Composable
fun Card() {
    Clickable(onClick = {
        Log.d("MATT", "You clicked on a card!")
        navigateTo(Screen.Details)
    }) {
        Card(shape = RoundedCornerShape(8.dp),
            modifier = Spacing(16.dp),
            elevation = 3.dp) {
            Padding(padding = 16.dp) {
                Container(height = 200.dp, expanded = true) {
                    Text("This is a card view",
                        style = (+MaterialTheme.typography()).h4  )
                }
            }
        }
    }
}

@Composable
fun VectorImageButton(@DrawableRes id: Int, onClick: () -> Unit) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(id = id)
        }
    }
}

@Composable
fun VectorImage(modifier: Modifier = Modifier.None, @DrawableRes id: Int, tint: Color = Color.Transparent) {
    val vector = +vectorResource(id)
    WithDensity {
        Container(
            modifier = modifier wraps Size(vector.defaultWidth.toDp(), vector.defaultHeight.toDp())
        ) {
            DrawVector(vector, tint)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}
