package ru.myapp.rickandmortyapi.ui.screens.search.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent
import ru.myapp.rickandmortyapi.ui.theme.RickAndMortyAPITheme

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersDialog(
    onClose: () -> Unit,
    dispatcher: (SearchEvent) -> Unit
) {
    val shape = 16.dp
    val filterName = remember { mutableStateOf("") }
    val filterStatus = remember { mutableStateOf("") }
    val filterSpecies = remember { mutableStateOf("") }
    val filterType = remember { mutableStateOf("") }
    val filterGender = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onClose
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.clip(RoundedCornerShape(shape)),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(shape, shape, 0.dp, 0.dp))
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Advanced search:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeName("name:")

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.width(200.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeName("status:")

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.width(200.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeName("species:")

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.width(200.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeName("type:")

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.width(200.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeName("gender:")

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.width(200.dp)
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, shape))
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
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(0.dp, 0.dp, shape, 0.dp))
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun FiltersDialogPreview() {
    RickAndMortyAPITheme {
        FiltersDialog(
            onClose = {},
            dispatcher = {}
        )
    }
}

@Composable
fun AttributeName(
    name: String
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}*/
