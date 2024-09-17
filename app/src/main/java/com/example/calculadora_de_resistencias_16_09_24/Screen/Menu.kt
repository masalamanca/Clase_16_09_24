package com.example.calculadora_de_resistencias_16_09_24.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle
import kotlin.math.pow


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Menu() {
    val context = LocalContext.current

    var banda1 by remember { mutableStateOf<String?>(null) }
    var banda2 by remember { mutableStateOf<String?>(null) }
    var banda3 by remember { mutableStateOf<String?>(null) }
    var tolerancia by remember { mutableStateOf<String?>(null) }
    var resistencia by remember { mutableStateOf<String>("") }

    // Mapear colores
    val colorMap = mapOf(
        "Negro" to Color.Black, "Marrón" to Color(0xFF8D6E63), "Rojo" to Color.Red,
        "Naranja" to Color(0xFFFF5722), "Amarillo" to Color.Yellow, "Verde" to Color.Green,
        "Azul" to Color.Blue, "Violeta" to Color(0xFF9C27B0), "Gris" to Color.Gray,
        "Blanco" to Color.White, "Dorado" to Color(0xFFFFD700), "Plata" to Color(0xFFC0C0C0)
    )

    // Función para calcular la resistencia
    fun calcularResistencia() {
        val colorCode = mapOf(
            "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3,
            "Amarillo" to 4, "Verde" to 5, "Azul" to 6, "Violeta" to 7,
            "Gris" to 8, "Blanco" to 9
        )

        val toleranciaCode = mapOf(
            "Dorado" to 0.05, "Plata" to 0.10, "Blanco" to 0.25
        )

        val valor1 = banda1?.let { colorCode[it] } ?: return
        val valor2 = banda2?.let { colorCode[it] } ?: return
        val valor3 = banda3?.let { colorCode[it] } ?: return
        val toleranciaValor = tolerancia?.let { toleranciaCode[it] } ?: return

        val multiplicador = 10.0.pow(valor3.toDouble())
        val resistenciaValor = (valor1 * 100 + valor2 * 10) * multiplicador
        resistencia = String.format("%.2f Ω ± %.2f%%", resistenciaValor, toleranciaValor * 100)
    }

    // Obtener el color actual basado en la banda seleccionada
    val buttonColor = when {
        banda1 != null -> colorMap[banda1] ?: Color.Gray
        banda2 != null -> colorMap[banda2] ?: Color.Gray
        banda3 != null -> colorMap[banda3] ?: Color.Gray
        else -> Color.Gray
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Calculadora de Código de Colores de Resistencias de 4 Bandas",
                            style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF87CEFA)) // Fondo de color sólido (color azul claro)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Banda 1
            DropdownMenuSelector(
                label = "Color Banda 1",
                selectedValue = banda1,
                onValueChange = { banda1 = it },
                menuItems = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco"),
                colorMap = colorMap
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Banda 2
            DropdownMenuSelector(
                label = "Color Banda 2",
                selectedValue = banda2,
                onValueChange = { banda2 = it },
                menuItems = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco"),
                colorMap = colorMap
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Banda 3
            DropdownMenuSelector(
                label = "Color Banda 3",
                selectedValue = banda3,
                onValueChange = { banda3 = it },
                menuItems = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco"),
                colorMap = colorMap
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tolerancia
            DropdownMenuSelector(
                label = "Color Tolerancia",
                selectedValue = tolerancia,
                onValueChange = { tolerancia = it },
                menuItems = listOf("Dorado", "Plata", "Blanco"),
                colorMap = colorMap
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { calcularResistencia() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp))
                    .background(buttonColor)
            ) {
                Text("Calcular Resistencia")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar la representación visual de la resistencia
            ResistenciaVisual(
                banda1 = banda1,
                banda2 = banda2,
                banda3 = banda3,
                tolerancia = tolerancia,
                colorMap = colorMap
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Resistencia: $resistencia",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuSelector(
    label: String,
    selectedValue: String?,
    onValueChange: (String) -> Unit,
    menuItems: List<String>,
    colorMap: Map<String, Color>
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        TextField(
            value = selectedValue ?: label,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .clip(RoundedCornerShape(30.dp))
                .background(color = colorMap[selectedValue ?: "Blanco"] ?: Color.White)
                .padding(16.dp)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .background(color = colorMap[item] ?: Color.White)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(item)
                        }
                    },
                    onClick = {
                        onValueChange(item)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ResistenciaVisual(
    banda1: String?,
    banda2: String?,
    banda3: String?,
    tolerancia: String?,
    colorMap: Map<String, Color>
) {
    // Mapea los colores seleccionados a los colores reales
    val colorBanda1 = colorMap[banda1] ?: Color.Gray
    val colorBanda2 = colorMap[banda2] ?: Color.Gray
    val colorBanda3 = colorMap[banda3] ?: Color.Gray
    val colorTolerancia = colorMap[tolerancia] ?: Color.Gray

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Banda 1
        Box(
            modifier = Modifier
                .size(60.dp, 25.dp)
                .background(colorBanda1, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "B1",
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Banda 2
        Box(
            modifier = Modifier
                .size(60.dp, 25.dp)
                .background(colorBanda2, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "B2",
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Banda 3
        Box(
            modifier = Modifier
                .size(60.dp, 25.dp)
                .background(colorBanda3, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "B3",
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Banda de Tolerancia (Opcional)
        Box(
            modifier = Modifier
                .size(60.dp, 25.dp)
                .background(colorTolerancia, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TOL",
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
