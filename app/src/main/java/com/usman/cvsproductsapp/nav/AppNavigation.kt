package com.usman.cvsproductsapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.usman.cvsproductsapp.ui.screens.ProductListScreen
import com.usman.cvsproductsapp.ui.screens.ProductUpdateScreen
import com.usman.cvsproductsapp.utils.routes.PRODUCT_LIST_ROUTE
import com.usman.cvsproductsapp.utils.routes.UPDATE_PRODUCT_ROUTE

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
      navController = navController,
        startDestination = PRODUCT_LIST_ROUTE
    ) {
        //Screens
        composable(PRODUCT_LIST_ROUTE){
            ProductListScreen(navController)
        }
        composable(UPDATE_PRODUCT_ROUTE){
            ProductUpdateScreen(navController)
        }
    }
}

