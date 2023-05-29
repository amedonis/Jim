package com.example.jim.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jim.data.model.ExerciseSet

@Dao
interface ExerciseSetDao {
    @Insert
    suspend fun insert(exerciseSet: ExerciseSet)


    @Query("SELECT * FROM exerciseset WHERE exerciseId = :exerciseId")
    fun getSetsByExercise(exerciseId: Int): LiveData<List<ExerciseSet>>


    @Delete
    suspend fun deleteExerciseSet(exerciseSet: ExerciseSet)
}