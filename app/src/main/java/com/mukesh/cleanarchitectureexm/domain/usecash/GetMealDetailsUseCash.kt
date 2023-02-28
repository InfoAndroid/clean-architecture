package com.mukesh.cleanarchitectureexm.domain.usecash

import com.mukesh.cleanarchitectureexm.common.Resource
import com.mukesh.cleanarchitectureexm.data.model.toDomainMealDetails
import com.mukesh.cleanarchitectureexm.domain.model.MealDetails
import com.mukesh.cleanarchitectureexm.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCash @Inject constructor(private val getMealDetailsRepository: GetMealDetailsRepository) {
    operator fun invoke(id: String): Flow<Resource<List<MealDetails>>> = flow {

        try {
            emit(Resource.Loading())
            val data = getMealDetailsRepository.getMealDetails(id)
            val response = if (!data.meals.isNullOrEmpty())data.meals.map { it -> it.toDomainMealDetails() } else emptyList()
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))

        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Internet Error"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }


    }
}