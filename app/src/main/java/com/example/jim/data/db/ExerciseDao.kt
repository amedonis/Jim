package com.example.jim.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(exercise: Exercise)


    @Query("SELECT * FROM exercise WHERE type = :type")
    fun getExercisesByType(type: String): LiveData<List<Exercise>>


    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}


