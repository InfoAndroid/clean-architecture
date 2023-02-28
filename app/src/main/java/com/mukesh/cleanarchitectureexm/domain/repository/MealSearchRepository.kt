package com.mukesh.cleanarchitectureexm.domain.repository

import com.mukesh.cleanarchitectureexm.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealList(s: String): MealsDTO
}