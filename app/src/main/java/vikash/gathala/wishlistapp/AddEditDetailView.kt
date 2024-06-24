package vikash.gathala.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.AnimBuilder
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import vikash.gathala.wishlistapp.data.Wish

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){
    rememberSystemUiController().setSystemBarsColor(colorResource(id = R.color.moderate_green))
    val snackMessage = remember{
        mutableStateOf("")
    }
    val keyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    if(id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState= wish.value.title
        viewModel.wishDescriptionState= wish.value.description
    }else{
        viewModel.wishTitleState= ""
        viewModel.wishDescriptionState= ""
    }
    
   Scaffold(scaffoldState= scaffoldState,
       topBar = {
       AppBar(
           title = if(id != 0L) stringResource(id = R.string.update_wish) else stringResource(id = R.string.add_wish)
, onBackPress = {
                navController.navigateUp()
           })
   }) {
       Column(modifier = Modifier
           .padding(it)
           .wrapContentSize()
           .background(color = colorResource(id = R.color.darkest_green))
           .fillMaxSize(),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally) {
           Spacer(modifier = Modifier.height(8.dp))
           InputField(label = "Title", value = viewModel.wishTitleState) {value->
               viewModel.onWishTitleChanged(value)
           }
           Spacer(modifier = Modifier.height(8.dp))
           InputField(label = "Description", value = viewModel.wishDescriptionState) {value->
               viewModel.onWishDescriptionChanged(value)
           }
           Spacer(modifier = Modifier.height(16.dp))
           Button(onClick = {
                            if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                                if(id != 0L){
                                    viewModel.updateAWish(Wish(
                                        id= id,
                                        title = viewModel.wishTitleState.trim(),
                                        description = viewModel.wishDescriptionState.trim()
                                    ))
                                    snackMessage.value= "Wish Updated"
                                }else{
                                    //TODO add a wish
                                    viewModel.addWish(
                                        Wish(
                                            title = viewModel.wishTitleState.trim(),
                                            description = viewModel.wishDescriptionState.trim()
                                        )
                                    )
                                    snackMessage.value= "Wish Created"
                                }
                            }else{
                                snackMessage.value= "Enter value in the fields for a wish"
                            }
               keyboard?.hide()
               scope.launch {
                   //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value, duration = SnackbarDuration.Short)
                   Toast.makeText(context, snackMessage.value, Toast.LENGTH_SHORT).show()
                   navController.navigateUp()
               }
           },
               colors = ButtonDefaults.buttonColors(colorResource(id = R.color.moderate_green)),
               shape = RoundedCornerShape(20.dp)
           ) {
               Text(text = if(id!=0L) stringResource(id = R.string.update_wish) else stringResource(
                   id = R.string.add_wish
               ),
                   style = TextStyle(
                       fontSize = 20.sp,
                       color = colorResource(id = R.color.light_green_text)
                   ),
                   modifier = Modifier.padding(4.dp)
               )
           }
       }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label:String,
    value:String,
    onValueChange:(String)->Unit
){
        OutlinedTextField(value = value, onValueChange = {onValueChange(it)},
            label = {
                Text(text = label, color = colorResource(id = R.color.green_text))
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = colorResource(id = R.color.light_green_text),
                unfocusedTextColor = colorResource(id = R.color.green_text),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = colorResource(id = R.color.light_green_text),
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.LightGray,
                containerColor = colorResource(id = R.color.moderate_green)
            )

        )
}




@Preview(showBackground = true)
@Composable
fun InputPreview(){
    InputField(label = "enter value", value = "") {

    }
}