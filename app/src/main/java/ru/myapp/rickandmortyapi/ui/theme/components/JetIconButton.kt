package ru.myapp.rickandmortyapi.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun JetIconButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    tint: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit
) {
    Icon(
        imageVector = ImageVector.vectorResource(iconId),
        contentDescription = null,
        tint = tint,
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(16.dp)

    )
}