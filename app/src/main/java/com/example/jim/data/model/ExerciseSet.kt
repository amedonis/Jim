package com.example.jim.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("exerciseId")],
    foreignKeys = [ForeignKey(
        entity = Exercise::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exerciseId: Int,
    val setNumber: Int,
    val weight: Double
)