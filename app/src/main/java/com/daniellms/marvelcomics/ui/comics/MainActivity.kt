package com.daniellms.marvelcomics.ui.comics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.daniellms.core.layout.items.BoxItemBottomBorder
import com.daniellms.core.layout.loading.ProgressLoadingCenter
import com.daniellms.marvelcomics.ui.comics.viewmodel.ListComicsViewModel
import com.daniellms.core.layout.theme.MarvelComicsTheme
import com.daniellms.marvelcomics.data.model.comics.Comic
import com.daniellms.marvelcomics.ui.comics.state.ComicsState
import com.daniellms.marvelcomics.ui.favorites.FavoritesActivity
import com.daniellms.marvelcomics.ui.favorites.state.FavoritesState
import com.daniellms.marvelcomics.ui.favorites.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var listComicsViewModel: ListComicsViewModel

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelComicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBarComics(listComicsViewModel)
                    }, content = { innerPadding ->
                        ListItemsPaginated(
                            listComicsViewModel,
                            favoritesViewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComics(listComicsViewModel: ListComicsViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getStringExtra("favorite_status")
            if (data == "back") {
                listComicsViewModel.refreshListState()
            }
        }
    }

    TopAppBar(
        title = { Text("Comics") },
        actions = {
            IconButton(onClick = {
                val intent = Intent(context, FavoritesActivity::class.java)
                launcher.launch(intent)
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Button",
                    tint = Color.Gray
                )
            }
        }
    )
}

@Composable
fun ListItemsPaginated(
    viewModel: ListComicsViewModel = viewModel(),
    favoritesViewModel: FavoritesViewModel = viewModel(),
    modifier: Modifier
) {
    val comicsState by viewModel.comicsState.collectAsState()
    val LIMIT_GET_COMICS = 15

    val lifecycleowner = LocalLifecycleOwner.current
    favoritesViewModel.favoriteMutableLiveData.observe(lifecycleowner) {
        when (it) {
            is FavoritesState.SavedFavorite -> {
                viewModel.refreshListState()
            }

            is FavoritesState.DeletedFavorite -> {
                viewModel.refreshListState()
            }

            else -> Unit
        }
    }

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
                        ComicItem(item, index + 1) { isFavorited ->
                            if (isFavorited)
                                item.comicFavorited?.let { favoritesViewModel.deleteFavorite(it) }
                            else
                                favoritesViewModel.saveFavorite(item)
                        }
                    }

                    item {
                        if (listComics.size < 100) {
                            viewModel.getComics(LIMIT_GET_COMICS)
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                Text(
                    text = "Total: ${listComics.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 8.dp, top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        is ComicsState.ErrorGetComics -> {
            Toast.makeText(LocalContext.current, "Erro. Tente novamente.", Toast.LENGTH_LONG).show()
        }

        is ComicsState.StartGet -> {
            viewModel.getComics(LIMIT_GET_COMICS)
            ProgressLoadingCenter()
        }

        else -> Unit
    }
}

@Composable
fun ComicItem(comic: Comic, orderIndex: Int, onFavoriteClick: (isFavorited: Boolean) -> Unit) {
    BoxItemBottomBorder {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "$orderIndex ",
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
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
                Text(text = comic.title ?: "", color = Color.Black, fontSize = 14.sp)
                Text(text = comic.variantDescription ?: "", color = Color.Gray, fontSize = 10.sp)
            }
        }

        IconButton(
            onClick = {
                onFavoriteClick.invoke(comic.isFavorited ?: false)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Button",
                tint = if (comic.isFavorited == true) Color.Red else Color.LightGray
            )

        }
    }
}

