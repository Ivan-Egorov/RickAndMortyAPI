package com.bluerose.fishgallery.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun AdvancedShadowPreview() {
    Box(
        Modifier
            .padding(32.dp)
            .size(72.dp, 48.dp)
            .advancedShadow(Color.Black, alpha = 0.15f, 8.dp, 4.dp, 4.dp)
            .background(
                Color.White, RoundedCornerShape(
                    8.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "1",
            color = Color.Black
        )
    }
}