package com.mukesh.cleanarchitectureexm.domain.usecash

import com.mukesh.cleanarchitectureexm.common.Resource
import com.mukesh.cleanarchitectureexm.data.model.toDomainMeal
import com.mukesh.cleanarchitectureexm.domain.model.Meal
import com.mukesh.cleanarchitectureexm.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCash @Inject constructor(private val mealSearchRepository: MealSearchRepository) {
    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            val data = mealSearchRepository.getMealList(s)
            val domainData =
                if (data.meals != null) data.meals.map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Internet Error"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }
}