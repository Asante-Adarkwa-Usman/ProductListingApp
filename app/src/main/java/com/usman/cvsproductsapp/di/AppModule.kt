package com.usman.cvsproductsapp.di


import com.usman.cvsproductsapp.data.BASE_URL
import com.usman.cvsproductsapp.data.api.ApiDetails
import com.usman.cvsproductsapp.data.repository.ProductsRepository
import com.usman.cvsproductsapp.data.repository.ProductsRepositoryImpl
import com.usman.cvsproductsapp.domain.ProductsUseCase
import com.usman.cvsproductsapp.domain.ProductsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //CoroutineDispatcher
    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    //Gson Converter
    @Provides
    @Singleton
    fun providesGsonConverter() : GsonConverterFactory = GsonConverterFactory.create()

    //Interceptor
    @Provides
    @Singleton
    fun provideOkHttpIntercepter() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    //Retrofit
    @Provides
    @Singleton
    fun providesRetrofit(
        gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gson)
            .build()
    }

    //Provides Retrofit Service
    @Provides
    @Singleton
    fun providesRetrofitService(retrofit: Retrofit): ApiDetails {
        return retrofit.create(ApiDetails::class.java)
    }

    @Provides
    @Singleton
    fun providesProductRepository(
        apiDetails: ApiDetails
    ): ProductsRepository = ProductsRepositoryImpl(apiDetails)

    @Provides
    @Singleton
    fun providesProductUseCase(
        productsRepository: ProductsRepository
    ) : ProductsUseCase = ProductsUseCaseImpl(productsRepository)



















}