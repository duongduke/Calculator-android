package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButtonColor(label: String, onClick: () -> Unit) {
    val backgroundColor = when (label) {
        "C" -> Color.Red
        "=", "+", "-", "×", "÷" -> Color(0xFF4CAF50)
        else -> Color.DarkGray
    }

    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 26.sp,
            color = Color.White
        )
    }
}
