package com.mukesh.cleanarchitectureexm.presentation.mealsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.mukesh.cleanarchitectureexm.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealSearchFragment : Fragment() {
    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!
    private val viewModel: MealSearchViewModel by viewModels()
    private val mealSearchAdapter = MealSearchAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)

        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//First data display
        viewModel.searchMealList(viewModel.getRandInput())

        binding.mealSearchRecycler.apply {
            adapter = mealSearchAdapter
        }

        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    viewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }

            }
        }


        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    it.mealID
                )
            )
        }

    }
}