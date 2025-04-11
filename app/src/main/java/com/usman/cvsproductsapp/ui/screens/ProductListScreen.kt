package com.usman.cvsproductsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usman.cvsproductsapp.ui.vm.ProductListViewModel
import com.usman.cvsproductsapp.ui.widgets.ProductCard
import com.usman.cvsproductsapp.utils.UiStatus


@Composable
fun ProductListScreen(navController: NavHostController, viewModel: ProductListViewModel = hiltViewModel()) {

    val productsUiState by viewModel.products.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (productsUiState) {
                is UiStatus.Loading -> {
                    CircularProgressIndicator()
                }
                is UiStatus.Success -> {
                    val productList = (productsUiState as UiStatus.Success).data
                    // Display the product list
                    Text("Product List Screen")
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn {
                        items(productList){ product ->
                            ProductCard(product = product)
                        }
                    }
                }
                is UiStatus.Error -> {
                    val errorMessage = (productsUiState as UiStatus.Error).message
                    Text("Error: $errorMessage")
                }
            }
        }
    }
}