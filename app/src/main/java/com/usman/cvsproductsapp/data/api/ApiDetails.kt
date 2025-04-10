package com.usman.cvsproductsapp.data.api

import com.usman.cvsproductsapp.data.model.ProductItemModel
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiDetails {
   //Get all products
    @GET(ApiReference.PRODUCTS_END_POINT)
    suspend fun getProducts(): Response<ArrayList<ProductItemModel>>

    @PUT(ApiReference.PRODUCT_DETAIL_END_POINT)
    suspend fun updateProduct(
        @Path(ApiReference.PRODUCT_DETAIL_END_POINT) id:Int,
        @Body product: ProductItemModel
    ): Response<ArrayList<ProductItemModel>>
}