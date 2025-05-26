package com.hkm.d4cshop.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.view.PixelCopy
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hkm.d4cshop.MainViewModel
import com.hkm.d4cshop.R
import com.hkm.d4cshop.composeutils.TopBar
import com.hkm.d4cshop.composeutils.dialogs.ShopAlertDialog
import com.hkm.d4cshop.navigation.ShopDestinations
import com.hkm.d4cshop.navigation.ShopNavGraph
import com.hkm.d4cshop.screens.shop.ShopScreen

@SuppressLint("ContextCastToActivity")
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val startDestination by remember { mutableStateOf(ShopDestinations.SHOP_ROUTE) }
    val context = LocalContext.current
    val activity = LocalActivity.current
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination


    val productInCart by viewModel.getCartProduct.collectAsState(emptyList())
    val productLiked by viewModel.getLikedProduct.collectAsState(emptyList())
    var totalLike by remember { mutableStateOf(0) }
    var totalCart by remember { mutableStateOf(0) }

    var topBarText by remember { mutableStateOf("Shop") }
    var showBadge by remember { mutableStateOf(true) }
    var rightButtonIcon by remember { mutableStateOf(Icons.Default.ShoppingCart) }
    var centerButtonIcon by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var leftButtonIcon by remember { mutableStateOf(Icons.Default.Search) }
    var showRightButton by remember { mutableStateOf(false) }
    var showLeftButton by remember { mutableStateOf(false) }
    var showCenterButton by remember { mutableStateOf(false) }
    var showRightCornerText by remember { mutableStateOf(false) }
    var rightCornerText by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    LaunchedEffect(productLiked, productInCart) {
        totalLike = productLiked.size
        totalCart = productInCart.size
    }
    LaunchedEffect(currentRoute,productLiked,productInCart){

        when(currentRoute){
            ShopDestinations.SHOP_ROUTE -> {
                topBarText = "Shop"
                showBadge = true
                rightButtonIcon = Icons.Default.ShoppingCart
                centerButtonIcon = Icons.Default.FavoriteBorder
                leftButtonIcon = Icons.Default.Search
                showRightButton = true
                showLeftButton = true
                showCenterButton = true
                showRightCornerText = false
            }
            ShopDestinations.CART_ROUTE -> {
               topBarText = "My Cart"
                showBadge = false
                rightButtonIcon = Icons.Default.Delete
                centerButtonIcon = Icons.Default.FavoriteBorder
                leftButtonIcon = Icons.Default.Search
                showRightButton = false
                showLeftButton = false
                showCenterButton = false
                showRightCornerText = totalCart > 0
                rightCornerText = "Remove all"
            }
            ShopDestinations.FAVOURITE_ROUTE -> {
                topBarText = "Favourite"
                showBadge = false
                rightButtonIcon = Icons.Default.Delete
                centerButtonIcon = Icons.Default.FavoriteBorder
                leftButtonIcon = Icons.Default.Search
                showRightButton = false
                showLeftButton = false
                showCenterButton = false
                showRightCornerText = totalLike > 0
                rightCornerText = "Remove all"
            }
            else -> {
                topBarText = "Shop"
                showBadge = true
                rightButtonIcon = Icons.Default.ShoppingCart
                centerButtonIcon = Icons.Default.FavoriteBorder
                leftButtonIcon = Icons.Default.Search
                showRightButton = false
                showLeftButton = false
                showCenterButton = false
                showRightCornerText = false
            }
        }
    }


    Scaffold(topBar = {
        TopBar(
            modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()), //  safe padding
            topBarText = topBarText,
            onBackClick = {
                navController.navigateUp()
            },
            onRightButtonClick = {
                when (currentRoute) {
                    ShopDestinations.SHOP_ROUTE -> {
                        navController.navigate(ShopDestinations.CART_ROUTE)
                    }

                    ShopDestinations.CART_ROUTE -> {

                    }
                }
            },
            onCenterButtonClick = {
                when (currentRoute) {

                    ShopDestinations.SHOP_ROUTE -> {
                        navController.navigate(ShopDestinations.FAVOURITE_ROUTE)
                    }

                    ShopDestinations.CART_ROUTE -> {

                    }
                }
            },
            onLeftButtonClick = {},
            likeBadgeCount = totalLike,
            cartBadgeCount = totalCart,
            showBadge = showBadge,
            rightButtonIcon = rightButtonIcon,
            centerButtonIcon = centerButtonIcon,
            leftButtonIcon = leftButtonIcon,
            showRightButton = showRightButton,
            showLeftButton = showLeftButton,
            showCenterButton = showCenterButton,
            showRightCornerText = showRightCornerText,
            rightCornerText = rightCornerText,
            onTextClick = {
                shouldShowDialog = true
            },
        )
    }) { innerPadding->

        Surface(modifier = Modifier.padding(innerPadding)) {
            if (shouldShowDialog){
                ShopAlertDialog(
                    message = when(currentRoute){
                        ShopDestinations.CART_ROUTE -> {
                            "Do you want remove all items from cart?"
                        }

                        ShopDestinations.FAVOURITE_ROUTE -> {
                            "Do you want remove all items from favourite?"
                        }
                        else -> {
                            ""
                        }
                    },
                    title = when(currentRoute){
                        ShopDestinations.CART_ROUTE -> {
                            "Remove"
                        }

                        ShopDestinations.FAVOURITE_ROUTE -> {
                            "Remove"
                        }
                        else -> {
                            ""
                        }
                    },
                    showNegativeButton = true,
                    positiveButtonText = "Cancel",
                    negativeButtonText = "Remove all",
                    onPositiveButtonClick = {
                        shouldShowDialog = false
                    },
                    onNegativeButtonClick = {
                        when(currentRoute){
                            ShopDestinations.CART_ROUTE -> {
                                viewModel.removeALlFromCart()
                            }

                            ShopDestinations.FAVOURITE_ROUTE -> {
                                viewModel.removeAllFromLiked()
                            }
                            else -> Unit
                        }
                        shouldShowDialog = false
                    }
                )
            }
            ShopNavGraph(
                navController = navController,
                startDestination = startDestination,
                currentNavBackStackEntry = currentNavBackStackEntry,
                currentRoute = currentRoute
            )
        }

    }
}