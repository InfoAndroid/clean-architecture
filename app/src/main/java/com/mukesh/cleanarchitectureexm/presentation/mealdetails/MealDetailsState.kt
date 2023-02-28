package com.mukesh.cleanarchitectureexm.presentation.meal_details

import com.mukesh.cleanarchitectureexm.domain.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
) {
}