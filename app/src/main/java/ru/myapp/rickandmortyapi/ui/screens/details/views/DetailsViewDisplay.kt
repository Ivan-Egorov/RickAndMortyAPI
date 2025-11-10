package ru.myapp.rickandmortyapi.ui.screens.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Spacer(modifier = Modifier.size(20.dp))

        AsyncImage(
            model = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
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

        Spacer(modifier = Modifier.size(20.dp))
    }

    /*Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Spacer(modifier = Modifier.size(20.dp))

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
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

            Spacer(modifier = Modifier.size(20.dp))
        }

        JetIconButtonCircle(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterStart),
            iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_left_24_regular,
            onClick = { dispatcher.invoke(DetailsEvent.Close) }
        )
    }*/

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
        color = Color.Black
    )
}