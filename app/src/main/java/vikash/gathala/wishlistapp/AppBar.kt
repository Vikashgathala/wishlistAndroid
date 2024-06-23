package vikash.gathala.wishlistapp

import android.content.res.Resources
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(title:String, onBackPress:()->Unit){

    val backButton: (@Composable () -> Unit)? = {
        IconButton(onClick = { onBackPress() }, modifier = Modifier.padding(top = 10.dp)) {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, tint = colorResource(id = R.color.light_green_text) ,contentDescription =null )
        }

    }

    TopAppBar(title = {
        Text( text= title,
            style = TextStyle(
            color = colorResource(id = R.color.green_text),
                fontSize = 24.sp
        ),
            modifier = Modifier
                .padding(start = 4.dp, top = 10.dp)
                .heightIn(max = 30.dp))
    },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.moderate_green),
        navigationIcon = if(!title.contains("WishList")) backButton else null)
}