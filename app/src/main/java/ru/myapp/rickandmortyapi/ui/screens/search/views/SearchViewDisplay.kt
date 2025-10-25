package ru.myapp.rickandmortyapi.ui.screens.search.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
    val searchField = rememberSaveable { mutableStateOf("") }
    val showFilters = rememberSaveable { mutableStateOf(false) }
    val filterStatus = rememberSaveable { mutableStateOf("") }
    val filterSpecies = rememberSaveable { mutableStateOf("") }
    val filterType = rememberSaveable { mutableStateOf("") }
    val filterGender = rememberSaveable { mutableStateOf("") }

    //val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val topPadding = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val startPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateStartPadding(LayoutDirection.Ltr) else 0.dp
    val endPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateEndPadding(LayoutDirection.Ltr) else 0.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier.size(topPadding + 56.dp)
            )

            // Filters
            if (showFilters.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.background(color = MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FilterName("status:")
                        }

                        TextField(
                            value = filterStatus.value,
                            onValueChange = {},
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FilterName("species:")
                        }

                        TextField(
                            value = filterSpecies.value,
                            onValueChange = { filterSpecies.value = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FilterName("type:")
                        }

                        TextField(
                            value = filterType.value,
                            onValueChange = { filterType.value = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FilterName("gender:")
                        }

                        TextField(
                            value = filterGender.value,
                            onValueChange = {},
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .clickable(onClick = {
                                    dispatcher.invoke(SearchEvent.Search(searchField.value))
                                })
                                .padding(vertical = 16.dp)
                        ) {
                            Text(
                                text = "Apply",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .clickable(onClick = {
                                    showFilters.value = false
                                    filterStatus.value = ""
                                    filterSpecies.value = ""
                                    filterType.value = ""
                                    filterGender.value = ""
                                })
                                .padding(vertical = 16.dp)
                        ) {
                            Text(
                                text = "Discard",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                }
            }

            // Characters
            LazyVerticalGrid(
                modifier = Modifier.padding(start = startPadding, end = endPadding),
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

                item {
                    Spacer(
                        modifier = Modifier
                            .size(bottomPadding + 8.dp)
                    )
                }
            }
        }

        // Search panel
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
                value = searchField.value,
                onValueChange = { searchField.value = it },
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
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_search_24_regular,
                onClick = { dispatcher.invoke(SearchEvent.Search(searchField.value)) }
            )

            JetIconButton(
                iconId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_filter_24_regular,
                onClick = { showFilters.value = true }
            )
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

@Composable
fun FilterName(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}