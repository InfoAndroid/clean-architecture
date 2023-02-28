package com.mukesh.cleanarchitectureexm.presentation.mealsearch

import com.mukesh.cleanarchitectureexm.domain.model.Meal

data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)
