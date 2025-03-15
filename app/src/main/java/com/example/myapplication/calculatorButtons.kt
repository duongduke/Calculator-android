package com.example.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalculatorButtons(
    expression: String,
    result: String,
    lastResult: String,
    isNewCalculation: Boolean,
    onUpdate: (String, String, String, Boolean) -> Unit
) {
    // Danh sách các nút trên bàn phím máy tính
    val buttons = listOf(
        listOf("C", "⌫", "+/-", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("%", "0", ".", "=")
    )

    // Vẽ giao diện bàn phím máy tính
    buttons.forEach { row ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            row.forEach { label ->
                CalculatorButtonColor(label) {
                    when (label) {
                        "C" -> onUpdate("", "0", "", false) // Xóa toàn bộ biểu thức
                        "⌫" -> onUpdate(if (expression.isNotEmpty()) expression.dropLast(1) else "", result, lastResult, false) // Xóa ký tự cuối
                        "=" -> {
                            val newResult = evaluateExpression(expression) // Tính toán kết quả
                            onUpdate(expression, newResult, newResult, true)
                        }
                        "%" -> onUpdate(expression + "%", result, lastResult, false) // Thêm dấu phần trăm
                        "+/-" -> {
                            if (isNewCalculation) {
                                // Nếu vừa bấm "=", đổi dấu của kết quả
                                val newResult = if (result.startsWith("-")) result.drop(1) else "-$result"
                                onUpdate(newResult, newResult, newResult, true)
                            } else {
                                // Nếu chưa bấm "=", đổi dấu của số cuối trong biểu thức
                                val regex = "[0-9.]+$".toRegex()
                                val match = regex.find(expression)
                                val newExpression = if (match != null) {
                                    val lastNumber = match.value
                                    val updatedNumber = if (lastNumber.startsWith("-")) lastNumber.drop(1) else "-$lastNumber"
                                    expression.replaceRange(match.range, updatedNumber)
                                } else expression
                                onUpdate(newExpression, result, lastResult, false)
                            }
                        }
                        else -> {
                            // Nếu là phép tính mới, chỉ lấy kết quả cuối cùng
                            val newExpression = if (isNewCalculation) {
                                if (label in listOf("+", "-", "×", "÷")) lastResult + label else label
                            } else expression + label
                            onUpdate(newExpression, result, lastResult, false)
                        }
                    }
                }
            }
        }
    }
}
