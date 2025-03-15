package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorApp() {
    // Các biến trạng thái để lưu trữ biểu thức, kết quả và trạng thái của phép tính
    var expression by remember { mutableStateOf("") } // Biểu thức hiện tại
    var result by remember { mutableStateOf("0") } // Kết quả tính toán
    var lastResult by remember { mutableStateOf("") } // Kết quả trước đó
    var isNewCalculation by remember { mutableStateOf(false) } // Xác định có phải phép tính mới không

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Đặt nền đen cho ứng dụng
            .padding(16.dp)
    ) {
        // Khu vực hiển thị biểu thức và kết quả
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Chiếm phần lớn không gian màn hình
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = expression, // Hiển thị biểu thức
                color = Color.White,
                fontSize = 78.sp,
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = result, // Hiển thị kết quả
                color = Color.Gray,
                fontSize = 62.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(20.dp)) // Khoảng cách giữa phần hiển thị và bàn phím

        // Bàn phím máy tính
        Column(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            CalculatorButtons(expression, result, lastResult, isNewCalculation)
            { newExpression, newResult, newLastResult, newIsNewCalculation ->
                if (result == "Error!") {
                    // Nếu có lỗi xảy ra, reset toàn bộ trạng thái
                    expression = ""
                    result = "0"
                    lastResult = ""
                    isNewCalculation = false
                } else {
                    // Cập nhật giá trị mới từ các nút bấm
                    expression = newExpression
                    result = newResult
                    lastResult = newLastResult
                    isNewCalculation = newIsNewCalculation
                }
            }
        }
    }
}