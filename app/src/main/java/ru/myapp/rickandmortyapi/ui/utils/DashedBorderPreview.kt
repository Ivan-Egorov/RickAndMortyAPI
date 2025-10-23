package ru.myapp.rickandmortyapi.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun DashedBorderPreview() {
    Box(
        Modifier
            .size(32.dp)
            .dashedBorder(
                2.dp, Color.Black, CircleShape, on = 4.dp, off = 4.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "1",
            color = Color.Black
        )
    }
}