package com.usman.cvsproductsapp.domain

import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow

interface ProductsUseCase {
    //all product list
    suspend fun fetchProductList() : Flow<UiStatus<ArrayList<ProductItemModel>>>

    //update product
    suspend fun updateProduct(id: Int, product: ProductItemModel): Flow<UiStatus<ProductItemModel>>

    //get product by Id
    suspend fun getSpecificProductById(id:Int): Flow<UiStatus<ProductItemModel>>
}