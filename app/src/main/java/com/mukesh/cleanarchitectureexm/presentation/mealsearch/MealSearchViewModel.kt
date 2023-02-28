package com.mukesh.cleanarchitectureexm.presentation.mealsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.cleanarchitectureexm.common.Resource
import com.mukesh.cleanarchitectureexm.domain.usecash.GetMealSearchListUseCash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCash: GetMealSearchListUseCash) :
    ViewModel() {
    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList: StateFlow<MealSearchState> = _mealSearchList
    var myList: List<String> = Arrays.asList("chicken", "apple", "egg", "veg","roasted")

    fun searchMealList(s: String) {
        getMealSearchListUseCash(s).onEach {
            when(it){
                is Resource.Loading->{
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Error->{
                    _mealSearchList.value = MealSearchState(error = it.message?:"")
                }
                is Resource.Success->{
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRandInput(): String {
        val r = Random()
        val randomItem = r.nextInt(myList.size)
        return myList[randomItem]
    }
}