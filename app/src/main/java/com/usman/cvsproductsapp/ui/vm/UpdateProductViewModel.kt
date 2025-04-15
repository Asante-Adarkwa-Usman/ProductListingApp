package com.usman.cvsproductsapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.domain.ProductsUseCase
import com.usman.cvsproductsapp.utils.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    // UI State for Product Update
    private val _updatedProduct = MutableStateFlow<UiStatus<ProductItemModel>>(UiStatus.Loading)
    val updatedProduct: StateFlow<UiStatus<ProductItemModel>> = _updatedProduct.asStateFlow()

    // UI State for Selected Product
    private val _selectedProduct = MutableStateFlow<ProductItemModel?>(null)
    val selectedProduct: StateFlow<ProductItemModel?> = _selectedProduct.asStateFlow()

    //UI state for product by id
    private val _specificProduct = MutableStateFlow<UiStatus<ProductItemModel?>>(UiStatus.Loading)
    val specificProduct: StateFlow<UiStatus<ProductItemModel?>> = _specificProduct.asStateFlow()

    // Set the selected product when navigating to the ProductUpdateScreen
    fun setSelectedProduct(product: ProductItemModel) {
        _selectedProduct.value = product
    }

    // Update the product (when the user clicks "Save")
    fun updateProduct(id: Int, product: ProductItemModel) {
        viewModelScope.launch(dispatcher) {
            productsUseCase.updateProduct(id, product).collect { uiStatus ->
                _updatedProduct.value = uiStatus
            }
        }
    }

    fun fetchSpecificProduct(id: Int){
        viewModelScope.launch(dispatcher) {
            productsUseCase.getSpecificProductById(id).collect{ product ->
                _specificProduct.value = product
            }
        }
    }

    // Update the price in the selected product before updating
    fun updatePrice(newPrice: Double) {
        _selectedProduct.value?.let { currentProduct ->
            _selectedProduct.value = currentProduct.copy(price = newPrice)
        }
    }
}