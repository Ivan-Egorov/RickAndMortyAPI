package ru.myapp.rickandmortyapi.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.json.JSONObject
import ru.myapp.rickandmortyapi.domain.models.CharacterPreview
import ru.myapp.rickandmortyapi.domain.models.Gender
import ru.myapp.rickandmortyapi.domain.models.Status
import ru.myapp.rickandmortyapi.ui.screens.details.models.DetailsEvent
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent

class SearchViewModel: ViewModel() {
    val baseUrl = "https://rickandmortyapi.com/api/character"
    var url = baseUrl

    @Composable
    fun LoadCharacters(
        dispatcher: (SearchEvent) -> Unit
    ) {
        GetByUrl(dispatcher = dispatcher, url = url)
    }

    fun changeUrl(url: String) {
        this.url = url
    }

    fun search(
        searchField: String,
        filterStatus: Status,
        filterSpecies: String,
        filterType: String,
        filterGender: Gender,
    ) {
        val urlBuilder: StringBuilder = StringBuilder(baseUrl)
        //url = "$baseUrl/?name=${searchField}"
        if (searchField != "" ||
            filterStatus.name != "ANY" ||
            filterSpecies != "" ||
            filterType != "" ||
            filterGender.name != "ANY") {

            urlBuilder.append("/?")

            if (searchField != "") {
                urlBuilder.append("name=${searchField}&")
            }

            if (filterStatus.name != "ANY") {
                urlBuilder.append("status=${filterStatus.name.lowercase()}&")
            }

            if (filterSpecies != "") {
                urlBuilder.append("species=${filterSpecies}&")
            }

            if (filterType != "") {
                urlBuilder.append("type=${filterType}&")
            }

            if (filterGender.name != "ANY") {
                urlBuilder.append("gender=${filterGender.name.lowercase()}&")
            }
        }

        url = urlBuilder.toString().substring(0, urlBuilder.length - 1)
        Log.e("url", url)
    }

    @Composable
    fun GetByUrl(
        dispatcher: (SearchEvent) -> Unit,
        url: String
    ) {
        var previousPage = "null"
        var nextPage = "null"
        val listOfCharacters = mutableListOf<CharacterPreview>()

        val client = HttpClient()
        LaunchedEffect(key1 = Unit) {

            try {
                val response = client.get(url)
                val jsonResponse: JSONObject = JSONObject(response.bodyAsText())

                val jsonInfo = jsonResponse.getJSONObject("info")
                previousPage = jsonInfo.getString("prev")
                nextPage = jsonInfo.getString("next")

                val jsonResults = jsonResponse.getJSONArray("results")
                for (i in 0..<jsonResults.length()) {
                    val character = jsonResults.getJSONObject(i)
                    listOfCharacters.add(CharacterPreview(
                        id = character.getInt("id"),
                        name = character.getString("name"),
                        status = character.getString("status"),
                        gender = character.getString("gender"),
                        species = character.getString("species"),
                        imagePath = character.getString("image"),
                    ))
                }

            } catch (e: Exception) {
                e.localizedMessage ?: "error"
            }
            finally {
                client.close()
            }

            dispatcher.invoke(SearchEvent.EnterScreen(
                previousPage = previousPage,
                nextPage = nextPage,
                listOfCharacters = listOfCharacters
            ))
        }
    }
}


