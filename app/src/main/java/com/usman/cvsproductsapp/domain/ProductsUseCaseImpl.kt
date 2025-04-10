package com.usman.cvsproductsapp.domain

import com.usman.cvsproductsapp.data.model.ProductItemModel
import com.usman.cvsproductsapp.data.repository.ProductsRepository
import com.usman.cvsproductsapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsUseCaseImpl@Inject constructor(
    private val productsRepository: ProductsRepository
): ProductsUseCase {
    override suspend fun fetchProductList(): Flow<UiStatus<ArrayList<ProductItemModel>>> {
        return productsRepository.getAllProducts()
    }

    override suspend fun updateProduct(id: Int, product: ProductItemModel): Flow<UiStatus<ArrayList<ProductItemModel>>> {
       return productsRepository.updateProduct(id,product)
    }



}

