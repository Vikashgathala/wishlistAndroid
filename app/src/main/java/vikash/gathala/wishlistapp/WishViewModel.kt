package vikash.gathala.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import vikash.gathala.wishlistapp.data.Wish
import vikash.gathala.wishlistapp.data.WishDao
import vikash.gathala.wishlistapp.data.WishRepository

class WishViewModel(private val wishRepository: WishRepository= Graph.wishRepository): ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newValue:String){
        wishTitleState=newValue
    }

    fun onWishDescriptionChanged(newValue:String){
        wishDescriptionState=newValue
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch {
            wishRepository.addWish(wish)
        }
    }

    fun deleteAWish(wish: Wish){
        viewModelScope.launch {
            wishRepository.deleteAWish(wish)
        }
    }

    fun updateAWish(wish: Wish){
        viewModelScope.launch {
            wishRepository.updateWish(wish)
        }
    }
    fun getAWishById(id:Long):Flow<Wish>{
            return wishRepository.getWishById(id)

    }
}