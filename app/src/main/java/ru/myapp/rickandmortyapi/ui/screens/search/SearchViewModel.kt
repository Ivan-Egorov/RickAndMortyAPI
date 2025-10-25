package ru.myapp.rickandmortyapi.ui.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import ru.myapp.rickandmortyapi.ui.screens.details.models.DetailsEvent
import ru.myapp.rickandmortyapi.ui.screens.search.models.SearchEvent

class SearchViewModel: ViewModel() {
    val baseUrl = "https://rickandmortyapi.com/api/character"

    /*@Composable
    fun GetByName(
        dispatcher: (SearchEvent) -> Unit,
        name: String
    ) {
        GetByUrl(dispatcher, "$baseUrl/?name=$name")
    }*/

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
                //val response = client.get("https://rickandmortyapi.com/api/character/?name=rick&status=dead")
                //val response = client.get("https://rickandmortyapi.com/api/character/?name=rick")
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


