package com.mukesh.cleanarchitectureexm.domain.repository

import com.mukesh.cleanarchitectureexm.data.model.MealsDTO

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id: String):MealsDTO
}