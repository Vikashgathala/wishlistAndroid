package vikash.gathala.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(WishEntity: Wish)

    @Query("Select * from `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(WishEntity: Wish)

    @Delete
    abstract suspend fun deleteAWish(WishEntity: Wish)

    @Query("select * from `wish-table` where id=:id")
    abstract fun getAWish(id:Long) : Flow<Wish>
}