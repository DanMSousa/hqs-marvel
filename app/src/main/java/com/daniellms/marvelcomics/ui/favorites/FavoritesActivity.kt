package com.daniellms.marvelcomics.ui.favorites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.daniellms.core.layout.items.BoxItemBottomBorder
import com.daniellms.core.layout.theme.MarvelComicsTheme
import com.daniellms.marvelcomics.data.room.model.ComicFavorite
import com.daniellms.marvelcomics.ui.favorites.state.FavoritesState
import com.daniellms.marvelcomics.ui.favorites.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesActivity : ComponentActivity() {

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MarvelComicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBarFavorites()
                    }
                ) { innerPadding ->
                    ListItemsFavorites(favoritesViewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarFavorites() {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val context = LocalContext.current
    val activity = context as? Activity

    TopAppBar(
        title = { Text("Favorites") },
        navigationIcon = {
            IconButton(onClick = {
                val resultIntent = Intent()
                resultIntent.putExtra("favorite_status", "back")
                activity?.setResult(Activity.RESULT_OK, resultIntent)
                onBackPressedDispatcher?.onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
        }
    )
}

@Composable
fun ListItemsFavorites(viewModel: FavoritesViewModel, modifier: Modifier) {
    val favoriteState =
        viewModel.favoriteMutableLiveData.observeAsState(FavoritesState.StartToGetFavorites)

    when (favoriteState.value) {
        is FavoritesState.SavedFavorite -> {
            // ignore
        }

        is FavoritesState.DeletedFavorite -> {
            viewModel.getAllFavorites()
        }

        is FavoritesState.GetComicsFavorite -> {
            val listFavorites = (favoriteState.value as FavoritesState.GetComicsFavorite).favorites
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(listFavorites) { item ->
                        FavoriteComicItem(item) { viewModel.deleteFavorite(item) }
                    }
                }

                Text(
                    text = "Total: ${listFavorites.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 8.dp, top = 8.dp),
                    textAlign = TextAlign.Center
                )

            }
        }

        is FavoritesState.StartToGetFavorites -> {
            viewModel.getAllFavorites()
        }
    }
}

@Composable
fun FavoriteComicItem(comic: ComicFavorite, onFavoriteClick: () -> Unit) {
    BoxItemBottomBorder {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(2.dp)
            ) {
                AsyncImage(
                    model = comic.imageUrl,
                    contentDescription = "HQ image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(84.dp)
                        .padding(end = 8.dp)

                )
                Text(
                    text = "US\$ ${comic.price} ",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

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
                onFavoriteClick.invoke()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Button",
                tint = Color.Red
            )
        }
    }
}

