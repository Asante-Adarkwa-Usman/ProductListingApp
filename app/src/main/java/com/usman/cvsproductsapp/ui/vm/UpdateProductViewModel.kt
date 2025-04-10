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
class UpdateProductViewModel@Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val productsUseCase: ProductsUseCase
):ViewModel() {

    private val _updatedProduct = MutableStateFlow<UiStatus<ArrayList<ProductItemModel>>>(UiStatus.Loading)
    val updatedProduct: StateFlow<UiStatus<ArrayList<ProductItemModel>>> = _updatedProduct.asStateFlow()

    //Update products
    fun updateProduct(id:Int, product:ProductItemModel){
        viewModelScope.launch(dispatcher) {
            productsUseCase.updateProduct(id,product).collect{
                _updatedProduct.value = it
            }
        }
    }
}