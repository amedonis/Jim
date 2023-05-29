package com.example.jim.presentation.exercise.viewmodel

import androidx.lifecycle.*
import com.example.jim.data.repository.ExerciseRepository
import com.example.jim.data.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: ExerciseRepository
) :
    ViewModel() {
    fun insert(exercise: Exercise) = viewModelScope.launch {
        repository.insert(exercise)
    }


    fun getExercises(exerciseType: String): LiveData<List<Exercise>> {
        return repository.getExercisesByType(exerciseType)

    }


    fun delete(exercise: Exercise) = viewModelScope.launch {
        repository.deleteSelectedExercise(exercise)
    }
}