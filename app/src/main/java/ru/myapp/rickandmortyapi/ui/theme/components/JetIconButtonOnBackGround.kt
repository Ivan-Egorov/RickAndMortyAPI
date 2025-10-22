package ru.myapp.rickandmortyapi.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.bluerose.fishgallery.ui.utils.advancedShadow

@Composable
fun JetIconButtonOnBackground(
    modifier: Modifier = Modifier,
    iconId: Int,
    tint: Color = MaterialTheme.colorScheme.surface,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .advancedShadow(
                color = Color.Black,
                alpha = 0.25f,
                cornersRadius = 28.dp,
                shadowBlurRadius = 2.dp,
                offsetY = 2.dp
            )
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape)
    ) {
        JetIconButton(
            iconId = iconId,
            tint = tint,
            onClick = onClick
        )
    }
}