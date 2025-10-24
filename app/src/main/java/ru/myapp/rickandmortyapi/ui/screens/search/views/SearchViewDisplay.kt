package ru.myapp.rickandmortyapi.ui.screens.search.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ru.myapp.rickandmortyapi.domain.models.CharacterPreview
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.utils.advancedShadow
import ru.myapp.rickandmortyapi.ui.theme.components.JetCard
import ru.myapp.rickandmortyapi.ui.theme.components.JetIconButton
import ru.myapp.rickandmortyapi.ui.theme.components.JetIconButtonCircle

@Composable
fun SearchViewDisplay(
    previousPage: String,
    nextPage: String,
    listOfCharacters: List<CharacterPreview>,
    dispatcher: (SearchEvent) -> Unit
) {
    val searchFieldValue = rememberSaveable { mutableStateOf("") }

    //val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val topPadding = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val startPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateStartPadding(LayoutDirection.Ltr) else 0.dp
    val endPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateEndPadding(LayoutDirection.Ltr) else 0.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(top = topPadding + 56.dp, start = startPadding, end = endPadding),
            columns = GridCells.FixedSize(190.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(listOfCharacters.size) { index ->
                val character = listOfCharacters.get(index)
                JetCard(
                    name = character.name,
                    status = character.status,
                    gender = character.gender,
                    species = character.species,
                    //imagePath = "file:///android_asset/img.png",
                    imagePath = character.imagePath,
                    //imagePath = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp),
                    onClick = { dispatcher.invoke(SearchEvent.OpenDetails(character.id)) }
                )

            }

            item {
                Text(previousPage)
            }

            item {
                Text(nextPage)
            }

            /*items(20) {
                JetCard(
                    name = "Trandor",
                    status = "Alive",
                    gender = "Male",
                    race = "Alien",
                    imagePath = "file:///android_asset/img.png",
                    //imagePath = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
                ) {}
            }*/

            item {
                Spacer(
                    modifier = Modifier
                        .size(bottomPadding + 8.dp)
                )
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .advancedShadow(
                    color = Color.Black,
                    alpha = 0.25f,
                    shadowBlurRadius = 2.dp,
                    offsetY = 2.dp)
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(
                    top = topPadding,
                    start = startPadding + 4.dp,
                    end = endPadding + 4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            TextField(
                value = searchFieldValue.value,
                onValueChange = { searchFieldValue.value = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                textStyle = MaterialTheme.typography.displayMedium,
                placeholder = {
                    Text(
                        text = "Search characters by name",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.surface
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary, // to hide line under the text
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary, // to hide line under the text
                )
            )

            JetIconButton(
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_search_24_regular
            ) { }

            JetIconButton(
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_filter_24_regular
            ) { }
        }

        if (previousPage != "null") {
            JetIconButtonCircle(
                modifier = Modifier
                    .padding(start = startPadding+ 4.dp)
                    .align(Alignment.CenterStart),
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_left_24_regular
            ) { dispatcher.invoke(SearchEvent.ChangePage(previousPage)) }
        }

        if (nextPage != "null") {
            JetIconButtonCircle(
                modifier = Modifier
                    .padding(end = endPadding + 4.dp)
                    .align(Alignment.CenterEnd),
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_right_24_regular
            ) { dispatcher.invoke(SearchEvent.ChangePage(nextPage)) }
        }
    }
}