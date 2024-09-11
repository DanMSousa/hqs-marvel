package com.daniellms.marvelcomics.ui.comics

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.daniellms.marvelcomics.ui.comics.viewmodel.ListComicsViewModel
import com.daniellms.core.layout.theme.MarvelComicsTheme
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.ui.comics.state.ComicsState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var listComicsViewModel: ListComicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelComicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListItemsPaginated(listComicsViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ListItemsPaginated(viewModel: ListComicsViewModel = viewModel(), modifier: Modifier) {
    val comicsState by viewModel.comicsState.collectAsState()
    val LIMIT_GET_COMICS = 15

    when (comicsState) {
        is ComicsState.SuccessGetComics -> {

            val listComics = (comicsState as ComicsState.SuccessGetComics).listComics
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(listComics) { index, item ->
                        ComicItem(item, index+1)
                    }

                    item {
                        if (listComics.size < 100) {
                            viewModel.getComics(LIMIT_GET_COMICS)
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                Text(
                    text = "quantidade: ${listComics.size}", style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 8.dp, top = 8.dp)
                    ,
                    textAlign = TextAlign.Center
                )
            }
        }

        is ComicsState.ErrorGetComics -> {
            Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_LONG).show()
        }

        is ComicsState.StartGet -> {
            viewModel.getComics(LIMIT_GET_COMICS)
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}


@Composable
fun ComicItem(comic: Comic, orderIndex: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "$orderIndex ", color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        AsyncImage(
            model = comic.thumbnail?.path + "." + comic.thumbnail?.extension,
            contentDescription = "HQ image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(84.dp)
                .padding(end = 8.dp)

        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text= comic.title ?: "", color = Color.Black, fontSize = 14.sp)
            Text(text = comic.variantDescription ?: "", color = Color.Gray, fontSize = 10.sp)
        }

    }
}