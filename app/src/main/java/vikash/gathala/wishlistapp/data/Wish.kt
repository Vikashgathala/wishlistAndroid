package vikash.gathala.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-title")
    var title: String = "",
    @ColumnInfo(name = "wish-desc")
    var description :  String = ""
)


object dummyWish{
    val wishList = listOf(
        Wish(title = "Google job", description = "get a google job"),
        Wish(title = "Get new iphone", description = "The best model in the market"),
        Wish(title = "Get a new car", description = "rolls royce in the end"),
        Wish(title = "Pay the loans", description = "pay all the bills and loans")
    )
}