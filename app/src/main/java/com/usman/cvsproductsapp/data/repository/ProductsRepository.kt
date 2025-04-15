package com.usman.cvsproductsapp.data.repository

import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    //Get all products
    suspend fun getAllProducts(): Flow<UiStatus<ArrayList<ProductItemModel>>>

    //update product list
    suspend fun updateProduct(id:Int, product: ProductItemModel): Flow<UiStatus<ProductItemModel>>

    //Get product by id
    suspend fun getSpecificProduct(id: Int): Flow<UiStatus<ProductItemModel>>
}