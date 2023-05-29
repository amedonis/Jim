package com.example.jim.presentation.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.jim.R
import com.example.jim.databinding.ActivityAddExerciseBinding
import com.example.jim.data.model.Exercise
import com.example.jim.presentation.exercise.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExerciseBinding
    private val viewModel: ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_exercise)


        val exerciseType = intent.getStringExtra("EXERCISE_TYPE").toString()


        binding.btnSave.setOnClickListener {
            if (binding.editTextExercise.text.toString().isNotEmpty()) {
                viewModel.insert(
                    Exercise(
                        0,
                        binding.editTextExercise.text.toString(),
                        exerciseType
                    )
                )
                Toast.makeText(this, "Exercise Saved!", Toast.LENGTH_SHORT).show()
                finish()

            } else Toast.makeText(this, "Please Enter The Name Of The Exercise!", Toast.LENGTH_LONG)
                .show()
        }


    }
}