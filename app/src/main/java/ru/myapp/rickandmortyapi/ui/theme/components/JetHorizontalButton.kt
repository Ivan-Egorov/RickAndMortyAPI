package ru.myapp.rickandmortyapi.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun JetHorizontalButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(54.dp)
            .background(
                color = backgroundColor,
                shape = shape)
            .clip(shape)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}