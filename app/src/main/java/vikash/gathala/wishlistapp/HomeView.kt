package vikash.gathala.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import vikash.gathala.wishlistapp.data.Wish
import vikash.gathala.wishlistapp.data.dummyWish

@Composable
fun HomeView(navController:NavController, viewModel: WishViewModel){
    val context = LocalContext.current
    Scaffold(modifier = Modifier
        .fillMaxSize() ,
        topBar = {
        AppBar("WishList App",{
            Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
        })
    },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                                           navController.navigate(Screen.AddScreen.route)
            }, modifier = Modifier.padding(all = 16.dp),
                contentColor = colorResource(id = R.color.green_text),
                containerColor = colorResource(id = R.color.moderate_green),
                shape = FloatingActionButtonDefaults.extendedFabShape) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) { innerPadding ->
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = colorResource(id = R.color.darkest_green))) {
            items(wishlist.value) {
                wishItem(wish = it) {
                    
                }
            }
        }
    }
}


@Composable
fun wishItem(wish: Wish, onClick:()->Unit){
    Card(
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large,
        backgroundColor = colorResource(id = R.color.moderate_green),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {

            }
    ) {
        Column(modifier = Modifier.padding(end = 20.dp, top = 12.dp, bottom = 12.dp, start = 20.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = wish.description)
        }
    }
}