package ru.myapp.rickandmortyapi.ui.screens.details.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.myapp.rickandmortyapi.ui.screens.details.models.DetailsEvent
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.theme.components.JetHorizontalButton

@Composable
fun DetailsViewDisplay(
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String,
    origin: String,
    location: String,
    image: String,
    episodes: List<String>,
    dispatcher: (DetailsEvent) -> Unit,
) {
    val scrollState = rememberScrollState()

    val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val topPadding = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val startPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateStartPadding(LayoutDirection.Ltr) else 0.dp
    val endPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateEndPadding(LayoutDirection.Ltr) else 0.dp
    val horizontalPadding = 16.dp

    if (landscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    top = topPadding,
                    bottom = bottomPadding,
                    start = startPadding + horizontalPadding,
                    end = endPadding + horizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            /*Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Box(modifier = Modifier.weight(1f))

                AsyncImage(
                    model = image,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(3f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillHeight
                )

                Box(modifier = Modifier.weight(1f))
            }*/

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                JetHorizontalButton(
                    text = "Return",
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { dispatcher.invoke(DetailsEvent.Close) }
                )

                DetailsTextTemplate("name: ", name)
                SimpleDivider()
                DetailsTextTemplate("status: ", status)
                SimpleDivider()
                DetailsTextTemplate("species: ", species)
                SimpleDivider()
                DetailsTextTemplate("type: ", type)
                SimpleDivider()
                DetailsTextTemplate("gender: ", gender)
                SimpleDivider()
                DetailsTextTemplate("origin: ", origin)
                SimpleDivider()
                DetailsTextTemplate("location: ", location)
                SimpleDivider()
                DetailsTextTemplate("episodes: ", episodes.toString())

            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(
                    top = topPadding,
                    bottom = bottomPadding,
                    start = startPadding + horizontalPadding,
                    end = endPadding + horizontalPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            /*Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.weight(1f))

                AsyncImage(
                    model = image,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(3f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillWidth
                )

                Box(modifier = Modifier.weight(1f))
            }*/

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillWidth
            )

            DetailsTextTemplate("name: ", name)
            SimpleDivider()
            DetailsTextTemplate("status: ", status)
            SimpleDivider()
            DetailsTextTemplate("species: ", species)
            SimpleDivider()
            DetailsTextTemplate("type: ", type)
            SimpleDivider()
            DetailsTextTemplate("gender: ", gender)
            SimpleDivider()
            DetailsTextTemplate("origin: ", origin)
            SimpleDivider()
            DetailsTextTemplate("location: ", location)
            SimpleDivider()
            DetailsTextTemplate("episodes: ", episodes.toString())

            JetHorizontalButton(
                text = "Return",
                backgroundColor = MaterialTheme.colorScheme.surface,
                textColor = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { dispatcher.invoke(DetailsEvent.Close) }
            )
        }
    }
}

@Composable
fun DetailsTextTemplate(
    firstLine: String,
    secondLine: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.W700)) {
                append(firstLine)
            }
            withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.W300)) {
                append(secondLine)
            }
        }
    )
}

@Composable
fun SimpleDivider() {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = Color.Black.copy(0.5f)
    )
}