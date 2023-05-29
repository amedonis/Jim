package com.example.jim.presentation.exerciseSet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.jim.data.model.Exercise
import com.example.jim.data.repository.ExerciseSetRepository
import com.example.jim.data.model.ExerciseSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseSetViewModel @Inject constructor(private val repository: ExerciseSetRepository) :
    ViewModel() {


    fun insert(exerciseSet: ExerciseSet) = viewModelScope.launch {
        repository.insert(exerciseSet)
    }


    fun getSetsByExercise(exerciseId: Int): LiveData<List<ExerciseSet>> {
        return repository.getSetsByExercise(exerciseId)
    }


    fun delete(exerciseSet: ExerciseSet) = viewModelScope.launch {
        repository.deleteSelectedExerciseSet(exerciseSet)
    }


}