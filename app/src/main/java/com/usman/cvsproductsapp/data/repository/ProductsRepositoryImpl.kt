package com.usman.cvsproductsapp.data.repository

import com.usman.cvsproductsapp.data.api.ApiDetails
import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiDetails: ApiDetails
): ProductsRepository {
    override suspend fun getAllProducts(): Flow<UiStatus<ArrayList<ProductItemModel>>> = flow {
         emit(UiStatus.Loading)
        try {
            val result = apiDetails.getProducts()
           if (result.isSuccessful){
               val productList = result.body()
               productList?.let {
                   emit(UiStatus.Success(productList))
               } ?: emit(UiStatus.Error("List is null"))
           } else emit(UiStatus.Error("Data retrieval failed"))
        } catch (e:Exception){
            emit(UiStatus.Error(e.toString()))
        }
    }

    override suspend fun updateProduct(id: Int, product: ProductItemModel): Flow<UiStatus<ProductItemModel>> = flow {
        emit(UiStatus.Loading)
        try {
            val result = apiDetails.updateProduct(id, product)
            if (result.isSuccessful) {
                val updatedProduct = result.body()
                updatedProduct?.let {
                    emit(UiStatus.Success(updatedProduct))
                } ?: emit(UiStatus.Error("Updated product is null or empty"))
            } else {
                emit(UiStatus.Error("Failed to update product"))
            }
        } catch (e: Exception) {
            emit(UiStatus.Error(e.toString()))
        }
    }

    override suspend fun getSpecificProduct(id: Int): Flow<UiStatus<ProductItemModel>> = flow{

        emit(UiStatus.Loading)
        try {
            val result = apiDetails.getProductById(id)
            if (result.isSuccessful){
                val specificProduct = result.body()
                specificProduct?.let {
                    emit(UiStatus.Success(specificProduct))
                } ?: emit(UiStatus.Error("List is null"))
            } else emit(UiStatus.Error("Data retrieval failed"))
        } catch (e:Exception){
            emit(UiStatus.Error(e.toString()))
        }
    }
}