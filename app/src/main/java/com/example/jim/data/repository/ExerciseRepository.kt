package com.example.jim.data.repository

import androidx.lifecycle.LiveData
import com.example.jim.data.model.Exercise
import com.example.jim.data.model.ExerciseDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) {
    suspend fun insert(exercise: Exercise) {
        exerciseDao.insert(exercise)
    }


    fun getExercisesByType(type: String): LiveData<List<Exercise>> {
        return exerciseDao.getExercisesByType(type)
    }


    suspend fun deleteSelectedExercise(exercise: Exercise){
        exerciseDao.deleteExercise(exercise)
    }
}