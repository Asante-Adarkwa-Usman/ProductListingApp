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
class ProductListViewModel@Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _products = MutableStateFlow<UiStatus<ArrayList<ProductItemModel>>>(UiStatus.Loading)
    val products: StateFlow<UiStatus<ArrayList<ProductItemModel>>> = _products.asStateFlow()

      init {
          getProductList()
      }

    //Get all products
    fun getProductList(){
        viewModelScope.launch(dispatcher) {
            productsUseCase.fetchProductList().collect{
               _products.value=it
            }
        }
    }
}