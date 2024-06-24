package vikash.gathala.wishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import vikash.gathala.wishlistapp.data.Wish
import vikash.gathala.wishlistapp.data.dummyWish

@OptIn(ExperimentalMaterialApi::class)
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
                                           navController.navigate(Screen.AddScreen.route + "/0L")
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
            items(wishlist.value, key = {wish-> wish.id}) {wish->
                val dismissState= rememberDismissState(
                    confirmStateChange = {
                        if(it==DismissValue.DismissedToStart){
                            viewModel.deleteAWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(state = dismissState, background = {
                    val color by animateColorAsState(
                        if(dismissState.dismissDirection==DismissDirection.EndToStart) Color.Red else Color.Transparent,
                        label = "Delete item"
                    )
                    val alignment = Alignment.CenterEnd
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(start = 16.dp, end = 16.dp)
                        .border(border = BorderStroke(0.dp, Color.Transparent),shape = RoundedCornerShape(10.dp)),
                        contentAlignment = alignment){
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Green)
                    }
                },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {FractionalThreshold(0.6 ..f)},
                    dismissContent = {
                        wishItem(wish = wish) {
                            val id=wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )

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
                onClick()
            }
    ) {
        Column(modifier = Modifier.padding(end = 20.dp, top = 12.dp, bottom = 12.dp, start = 20.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = wish.description)
        }
    }
}