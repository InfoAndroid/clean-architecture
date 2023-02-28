package com.mukesh.cleanarchitectureexm.hilt

import com.mukesh.cleanarchitectureexm.common.Constants
import com.mukesh.cleanarchitectureexm.data.remote.MealSearchAPI
import com.mukesh.cleanarchitectureexm.data.repositoryimp.GetMealDetailsImpl
import com.mukesh.cleanarchitectureexm.data.repositoryimp.GetMealListImpl
import com.mukesh.cleanarchitectureexm.domain.repository.GetMealDetailsRepository
import com.mukesh.cleanarchitectureexm.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object HiltModules {

/*    var interceptor: HttpLoggingInterceptor= run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()*/
    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealSearchAPI::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return GetMealListImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI):GetMealDetailsRepository{
        return GetMealDetailsImpl(mealSearchAPI);
    }
}