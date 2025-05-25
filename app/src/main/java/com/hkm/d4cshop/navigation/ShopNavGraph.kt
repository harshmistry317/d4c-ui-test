package com.hkm.d4cshop.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hkm.d4cshop.screens.cart.CartScreen
import com.hkm.d4cshop.screens.favourite.FavouriteScreen
import com.hkm.d4cshop.screens.shop.ShopScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun ShopNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = ShopDestinations.SHOP_ROUTE,
    navActions: ShopNavigationActions = remember(navController) {
        ShopNavigationActions(navController)
    },
    currentNavBackStackEntry: NavBackStackEntry?,
    currentRoute: String
) {



    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
//        enterTransition = { slideInHorizontally() },
//        exitTransition = { slideOutHorizontally() },
//        popEnterTransition = { slideInHorizontally() },
//        popExitTransition = { slideOutHorizontally() }

        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        }

    ){

        composable(ShopDestinations.SHOP_ROUTE) {
            ShopScreen()
        }
        composable(ShopDestinations.CART_ROUTE) {
            CartScreen()
        }
        composable(ShopDestinations.FAVOURITE_ROUTE) {
            FavouriteScreen()
        }

    }

}