package ru.myapp.rickandmortyapi.ui.screens.search.views

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.myapp.rickandmortyapi.domain.models.CharacterPreview
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.utils.advancedShadow
import ru.myapp.rickandmortyapi.ui.theme.components.JetCard
import ru.myapp.rickandmortyapi.ui.theme.components.JetHorizontalButton
import ru.myapp.rickandmortyapi.ui.theme.components.JetIconButton

@Composable
fun SearchViewDisplay(
    previousPage: String,
    nextPage: String,
    listOfCharacters: List<CharacterPreview>,
    dispatcher: (SearchEvent) -> Unit
) {
    val searchField = rememberSaveable { mutableStateOf("") }
    val showFilters = rememberSaveable { mutableStateOf(false) }
    val filterAliveStatus = rememberSaveable { mutableStateOf(false) }
    val filterDeadStatus = rememberSaveable { mutableStateOf(false) }
    val filterUnknownStatus = rememberSaveable { mutableStateOf(false) }
    val filterSpecies = rememberSaveable { mutableStateOf("") }
    val filterType = rememberSaveable { mutableStateOf("") }
    val filterMaleGender = rememberSaveable { mutableStateOf(false) }
    val filterFemaleGender = rememberSaveable { mutableStateOf(false) }
    val filterGenderless = rememberSaveable { mutableStateOf(false) }
    val filterUnknownGender = rememberSaveable { mutableStateOf(false) }

    //val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val topPadding = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.safeContent.asPaddingValues().calculateBottomPadding()
    val startPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateStartPadding(LayoutDirection.Ltr) else 0.dp
    val endPadding = if (landscape) WindowInsets.safeContent.asPaddingValues().calculateEndPadding(LayoutDirection.Ltr) else 0.dp
    val horizontalPadding = 16.dp

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
                        .verticalScroll(rememberScrollState())
                        //.background(color = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = startPadding + horizontalPadding,
                                end = endPadding + horizontalPadding),
                        //verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FilterName("status:")
                            }

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterAliveStatus.value,
                                    onCheckedChange = { filterAliveStatus.value = !filterAliveStatus.value }
                                )
                                FilterName("alive")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {}

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterDeadStatus.value,
                                    onCheckedChange = { filterDeadStatus.value = !filterDeadStatus.value }
                                )
                                FilterName("dead")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {}

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterUnknownStatus.value,
                                    onCheckedChange = { filterUnknownStatus.value = !filterUnknownStatus.value }
                                )
                                FilterName("unknown status")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FilterName("species:")
                            }

                            TextField(
                                value = filterSpecies.value,
                                onValueChange = { filterSpecies.value = it },
                                modifier = Modifier.weight(1f),
                                textStyle = MaterialTheme.typography.displayMedium,
                                placeholder = {
                                    Text(
                                        text = "any species",
                                        style = MaterialTheme.typography.displayMedium,
                                        color = Color.Black.copy(0.5f)
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                    cursorColor = MaterialTheme.colorScheme.onSurface,
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FilterName("type:")
                            }

                            TextField(
                                value = filterType.value,
                                onValueChange = { filterType.value = it },
                                modifier = Modifier.weight(1f),
                                textStyle = MaterialTheme.typography.displayMedium,
                                placeholder = {
                                    Text(
                                        text = "any type",
                                        style = MaterialTheme.typography.displayMedium,
                                        color = Color.Black.copy(0.5f)
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                                    cursorColor = MaterialTheme.colorScheme.onSurface,
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                FilterName("gender:")
                            }

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterMaleGender.value,
                                    onCheckedChange = { filterMaleGender.value = !filterMaleGender.value }
                                )
                                FilterName("male")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {}

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterFemaleGender.value,
                                    onCheckedChange = { filterFemaleGender.value = !filterFemaleGender.value }
                                )
                                FilterName("female")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {}

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterGenderless.value,
                                    onCheckedChange = { filterGenderless.value = !filterGenderless.value }
                                )
                                FilterName("genderless")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {}

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = filterUnknownGender.value,
                                    onCheckedChange = { filterUnknownGender.value = !filterUnknownGender.value }
                                )
                                FilterName("unknown gender")
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        JetHorizontalButton(
                            text = "Apply filters",
                            backgroundColor = MaterialTheme.colorScheme.surface,
                            textColor = Color.Black,
                            modifier = Modifier.weight(1f),
                            shape = RectangleShape,
                            onClick = { dispatcher.invoke(SearchEvent.Search(searchField.value)) }
                        )

                        JetHorizontalButton(
                            text = "Discard",
                            backgroundColor = MaterialTheme.colorScheme.surface,
                            textColor = Color.Black.copy(0.5f),
                            modifier = Modifier.weight(1f),
                            shape = RectangleShape,
                            onClick = {
                                showFilters.value = false
                                filterAliveStatus.value = false
                                filterDeadStatus.value = false
                                filterUnknownStatus.value = false
                                filterSpecies.value = ""
                                filterType.value = ""
                                filterMaleGender.value = false
                                filterFemaleGender.value = false
                                filterGenderless.value = false
                                filterUnknownGender.value = false
                            }
                        )
                    }
                }
            }

            // Characters table
            if (!listOfCharacters.isEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(
                        start = startPadding + horizontalPadding - 4.dp,
                        end = endPadding + horizontalPadding - 4.dp),
                    //columns = GridCells.FixedSize(190.dp),
                    columns = GridCells.Fixed(if (landscape) 4 else 2),
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

                    val numberOfEmptySockets = if (landscape) 4 - (listOfCharacters.size % 4) else listOfCharacters.size % 2
                    if (numberOfEmptySockets != 4 && numberOfEmptySockets != 0) {
                        items(numberOfEmptySockets) {
                            EmptySocket()
                        }
                    }

                    if (previousPage != "null" || nextPage != "null") {
                        if (previousPage != "null") {
                            item {
                                JetHorizontalButton(
                                    text = "Previous page",
                                    backgroundColor = MaterialTheme.colorScheme.primary,
                                    textColor = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp),
                                    onClick = { dispatcher.invoke(SearchEvent.ChangePage(previousPage)) }
                                )
                            }
                        } else {
                            item {
                                EmptySocket()
                            }
                        }

                        if (landscape) {
                            items(2) {
                                EmptySocket()
                            }
                        }

                        if (nextPage != "null") {
                            item {
                                JetHorizontalButton(
                                    text = "Next page",
                                    backgroundColor = MaterialTheme.colorScheme.primary,
                                    textColor = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
                                    onClick = { dispatcher.invoke(SearchEvent.ChangePage(nextPage)) }
                                )
                            }
                        } else {
                            item {
                                EmptySocket()
                            }
                        }
                    }

                    item {
                        Spacer(
                            modifier = Modifier
                                .size(bottomPadding + 8.dp)
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_fluent_calendar_empty_24_filled),
                            contentDescription = "",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        )
                        Text(
                            text = "No results found",
                            color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
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
                    start = startPadding + horizontalPadding,
                    end = endPadding + horizontalPadding),
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
                onClick = { showFilters.value = !showFilters.value }
            )
        }

        /*if (previousPage != "null") {
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
        }*/
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

@Composable
fun EmptySocket() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .height(56.dp)
    )
}