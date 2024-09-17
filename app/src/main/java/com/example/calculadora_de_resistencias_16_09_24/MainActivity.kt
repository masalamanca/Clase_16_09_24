package com.example.calculadora_de_resistencias_16_09_24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Menu
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora_de_resistencias_16_09_24.Screen.Menu
import com.example.calculadora_de_resistencias_16_09_24.ui.theme.Calculadora_de_resistencias_160924Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Menu()
        }
    }
}
