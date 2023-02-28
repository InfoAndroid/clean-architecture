package com.mukesh.cleanarchitectureexm.data.repositoryimp

import com.mukesh.cleanarchitectureexm.data.model.MealsDTO
import com.mukesh.cleanarchitectureexm.data.remote.MealSearchAPI
import com.mukesh.cleanarchitectureexm.domain.repository.MealSearchRepository

class GetMealListImpl(private val mealSearchAPI: MealSearchAPI):MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }
}