package com.usman.cvsproductsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.ui.vm.UpdateProductViewModel
import com.usman.cvsproductsapp.ui.widgets.ProductDetailsSection
import com.usman.cvsproductsapp.utils.UiStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductUpdateScreen(navController: NavHostController, productId: Int, viewModel: UpdateProductViewModel = hiltViewModel()) {

    val selectedProduct by viewModel.selectedProduct.collectAsState()
    val specificProduct by viewModel.specificProduct.collectAsState()
    val updatedProductState by viewModel.updatedProduct.collectAsState()
    var newPrice by remember { mutableStateOf("") }

    LaunchedEffect(productId) {
        viewModel.fetchSpecificProduct(productId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (specificProduct) {
                is UiStatus.Loading -> {
                    CircularProgressIndicator()
                }

                is UiStatus.Error -> {
                    Text("Error loading product: ${(specificProduct as UiStatus.Error).message}")
                }

                is UiStatus.Success -> {
                    val product = (specificProduct as UiStatus.Success<ProductItemModel?>).data
                    if (product != null && selectedProduct == null) {
                        // Set selected product here instead of LaunchedEffect
                        viewModel.setSelectedProduct(product)
                    }

                    selectedProduct?.let { it ->
                        ProductDetailsSection(
                            product = it,
                            newPrice = newPrice,
                            onPriceChange = { newPrice = it },
                            onSaveClick = { price ->
                                viewModel.updatePrice(price)
                                viewModel.updateProduct(it.id, it.copy(price = price))
                            },
                            updatedProductState = updatedProductState
                        )
                    }
                }
            }
        }
    }
}
