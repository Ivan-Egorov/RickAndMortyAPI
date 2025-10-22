package ru.myapp.rickandmortyapi.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bluerose.fishgallery.ui.utils.advancedShadow
import ru.myapp.rickandmortyapi.ui.theme.RickAndMortyAPITheme


@Composable
fun JetCard(
    modifier: Modifier = Modifier,
    name: String,
    status: String,
    gender: String,
    race: String,
    imagePath: String,
    onClick: () -> Unit,
) {
    val shape = 16.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .advancedShadow(
                color = Color.Black,
                alpha = 0.25f,
                cornersRadius = shape,
                shadowBlurRadius = 2.dp,
                offsetY = 2.dp
            )
            .clip(shape = RoundedCornerShape(shape))
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imagePath,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(shape, shape, 0.dp, 0.dp)),
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(shape, 0.dp, 0.dp, 0.dp))
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_add_circle_20_filled),
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier.size(10.dp)
                )

                Text(
                    text = status,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    //color = Color.LightGray,
                    shape = RoundedCornerShape(0.dp, 0.dp, shape, shape))
                .padding(top = 6.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
                //color = Color.Black
            )

            Text(
                text = "$gender | $race",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
                //color = Color.Black
            )
        }
    }
}

/*
@Preview
@Composable
private fun JetCardPreview() {
    RickAndMortyAPITheme {
        JetCard(
            modifier = Modifier.padding(20.dp),
            name = "Trandor",
            status = "Alive",
            gender = "Male",
            race = "Alien",
            imagePath = "https://rickandmortyapi.com/api/character/avatar/363.jpeg"
        ) {}
    }
}*/
