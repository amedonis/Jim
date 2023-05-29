package com.example.jim.presentation.exerciseSet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.jim.R
import com.example.jim.databinding.ActivityAddExerciseSetBinding
import com.example.jim.data.model.ExerciseSet
import com.example.jim.presentation.exerciseSet.viewmodel.ExerciseSetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseSetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExerciseSetBinding
    private val viewModel: ExerciseSetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_exercise_set)

        val exerciseID = intent.getIntExtra("EXERCISE_ID", 0)


        binding.btnSave.setOnClickListener {
            if (binding.editTextSetCount.text.isNotEmpty() && binding.editTextWeight.text.isNotEmpty()) {
                viewModel.insert(
                    ExerciseSet(
                        0,
                        exerciseID,
                        binding.editTextSetCount.text.toString().toInt(),
                        binding.editTextWeight.text.toString().toDouble()
                    )
                )
                Toast.makeText(this, "Exercise Saved!", Toast.LENGTH_SHORT).show()
                finish()

            } else Toast.makeText(this, "Entered Values Are Incorrect!", Toast.LENGTH_LONG)
                .show()

        }
    }
}