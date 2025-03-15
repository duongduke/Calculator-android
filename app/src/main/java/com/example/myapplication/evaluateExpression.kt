package com.example.calculator

import net.objecthunter.exp4j.ExpressionBuilder

fun evaluateExpression(expression: String): String {
    return try {
        // Chuyển đổi các ký tự toán học về định dạng mà thư viện exp4j hỗ trợ
        val modifiedExpression = expression.replace("×", "*").replace("÷", "/").replace("%", "/100")

        // Xây dựng và tính toán biểu thức
        val result = ExpressionBuilder(modifiedExpression).build().evaluate()

        // Nếu kết quả là số nguyên, hiển thị không có phần thập phân
        if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    } catch (e: Exception) {
        "Error!" // Trả về lỗi nếu có ngoại lệ xảy ra
    }
}

fun handleNegation(expression: String, result: String, isNewCalculation: Boolean): Pair<String, String> {
    return if (isNewCalculation) {
        // Nếu vừa nhấn "=", đổi dấu của kết quả thay vì số cuối
        val newResult = if (result.startsWith("-")) result.drop(1) else "-$result"
        newResult to newResult
    } else {
        // Nếu chưa nhấn "=", tìm số cuối trong biểu thức để đổi dấu
        val regex = Regex("[-+]?[0-9]*\\.?[0-9]+$")
        val match = regex.find(expression)

        if (match != null) {
            val number = match.value
            val newNumber = if (number.startsWith("-")) number.drop(1) else "-$number"
            expression.replace(regex, newNumber) to result
        } else {
            expression to result // Trả về nguyên biểu thức nếu không tìm thấy số để đổi dấu
        }
    }
}