package com.example.jim.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jim.R
import com.example.jim.databinding.ActivityMainBinding
import com.example.jim.presentation.exercise.ExerciseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnLegs.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java).apply {
                putExtra("EXERCISE_TYPE", "legs")
            }
            startActivity(intent)
        }


        binding.btnPush.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java).apply {
                putExtra("EXERCISE_TYPE", "pull")

            }
            startActivity(intent)
        }


        binding.btnPull.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java).apply {
                putExtra("EXERCISE_TYPE", "pull")

            }
            startActivity(intent)
        }


    }
}