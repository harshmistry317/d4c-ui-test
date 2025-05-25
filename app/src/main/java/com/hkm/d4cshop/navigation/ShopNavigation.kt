package com.hkm.d4cshop.navigation

import androidx.navigation.NavHostController


private object ShopScreens{
    const val SHOP_SCREEN = "shop"
    const val CART_SCREEN = "cart"
    const val FAVOURITE_SCREEN = "favourite"
}

object ShopDestinations{
    const val SHOP_ROUTE = ShopScreens.SHOP_SCREEN
    const val CART_ROUTE = ShopScreens.CART_SCREEN
    const val FAVOURITE_ROUTE = ShopScreens.FAVOURITE_SCREEN
}

class ShopNavigationActions(private val navController: NavHostController){
    fun navigateToCart(){
        navController.navigate(ShopDestinations.CART_ROUTE)
    }
    fun navigateToFavourite(){
        navController.navigate(ShopDestinations.FAVOURITE_ROUTE)
    }

}