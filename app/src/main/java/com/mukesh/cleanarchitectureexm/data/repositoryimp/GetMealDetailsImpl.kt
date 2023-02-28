package com.mukesh.cleanarchitectureexm.data.repositoryimp

import com.mukesh.cleanarchitectureexm.data.model.MealsDTO
import com.mukesh.cleanarchitectureexm.data.remote.MealSearchAPI
import com.mukesh.cleanarchitectureexm.domain.repository.GetMealDetailsRepository

class GetMealDetailsImpl(private val mealSearchAPI: MealSearchAPI):GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
       return mealSearchAPI.getMealDetails(id)
    }

}