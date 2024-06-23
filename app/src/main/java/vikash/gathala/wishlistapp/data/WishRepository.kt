package vikash.gathala.wishlistapp.data

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    suspend fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    suspend fun getWishById(id:Long):Flow<Wish>{
       return  wishDao.getAWish(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}