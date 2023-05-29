package com.example.jim.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.jim.data.db.ExerciseSetDao
import com.example.jim.data.model.ExerciseDao
import com.example.jim.data.model.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java,
            "exercise_database"
        ).build()
    }


    @Provides
    fun provideExerciseDao(database: ExerciseDatabase): ExerciseDao {
        return database.exerciseDao()
    }


    @Provides
    fun provideExerciseSetDao(database: ExerciseDatabase): ExerciseSetDao {
        return database.exerciseSetDao()
    }
}