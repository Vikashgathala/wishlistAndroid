package vikash.gathala.wishlistapp

sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_Screen")
    object AddScreen : Screen("add_screen")
}