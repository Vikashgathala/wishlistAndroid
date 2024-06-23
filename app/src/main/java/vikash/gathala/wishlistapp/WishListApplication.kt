package vikash.gathala.wishlistapp

import android.app.Application

class WishListApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}