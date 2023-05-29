package com.example.jim.data.repository

import androidx.lifecycle.LiveData
import com.example.jim.data.db.ExerciseSetDao
import com.example.jim.data.model.Exercise
import com.example.jim.data.model.ExerciseSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseSetRepository @Inject constructor(private val exerciseSetDao: ExerciseSetDao) {
    suspend fun insert(exerciseSet: ExerciseSet) {
        exerciseSetDao.insert(exerciseSet)
    }


    fun getSetsByExercise(exerciseId: Int): LiveData<List<ExerciseSet>> {
        return exerciseSetDao.getSetsByExercise(exerciseId)
    }


    suspend fun deleteSelectedExerciseSet(exerciseSet: ExerciseSet) {
        exerciseSetDao.deleteExerciseSet(exerciseSet)
    }
}