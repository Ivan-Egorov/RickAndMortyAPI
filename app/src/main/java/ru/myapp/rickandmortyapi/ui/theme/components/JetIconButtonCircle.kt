package ru.myapp.rickandmortyapi.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.myapp.rickandmortyapi.ui.theme.RickAndMortyAPITheme
import ru.myapp.rickandmortyapi.ui.utils.advancedShadow

/*
@Composable
fun JetIconButtonCircle(
    modifier: Modifier = Modifier,
    iconId: Int,
    //color: Color = MaterialTheme.colorScheme.primary,
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
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center

    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
    }
}

//@Preview
@Composable
fun JetIconButtonCirclePreview() {
    RickAndMortyAPITheme {
        JetIconButtonCircle(
            iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_caret_left_24_filled
        ) {}
    }
}*/
