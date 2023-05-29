package com.example.jim.presentation.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jim.presentation.exerciseSet.ExerciseSetActivity
import com.example.jim.R
import com.example.jim.data.model.Exercise
import com.example.jim.databinding.ActivityExerciseBinding
import com.example.jim.presentation.exercise.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private val viewModel: ExerciseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise)


        val exerciseType = intent.getStringExtra("EXERCISE_TYPE")


        val adapter = ExerciseAdapter()
        binding.recyclerViewExercises.adapter = adapter
        binding.recyclerViewExercises.layoutManager = LinearLayoutManager(this)
        viewModel.getExercises(exerciseType!!).observe(this, Observer { exercises ->
            if (exercises != null) {
                adapter.setExercises(exercises)
            }
        })


        // Swiping To Delete
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.getExerciseAt(position)
                onAlertDialog(item, viewHolder.itemView, position)
            }
        }


        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewExercises)


        binding.addExercise.setOnClickListener {
            val intent = Intent(this, AddExerciseActivity::class.java).apply {
                putExtra("EXERCISE_TYPE", exerciseType)
            }
            startActivity(intent)
        }


        // Click the item to change activities
        adapter.onItemClick = {
            val intent = Intent(this, ExerciseSetActivity::class.java).apply {
                putExtra("exercise", it)
            }
            startActivity(intent)
        }


    }


    fun onAlertDialog(item: Exercise, view: View, position: Int) {
            // Instantiate builder variable
        val builder = AlertDialog.Builder(view.context)

            // Set title
        builder.setTitle("Deleting Exercise")

            // Set content area
        builder.setMessage("Do you want to delete the exercise?")

            // Set negative button
        builder.setPositiveButton(
            "Delete"
        ) { dialog, id ->
            // User clicked Delete button
            viewModel.delete(item)
            Toast.makeText(this, "Deleted!",Toast.LENGTH_SHORT).show()
        }

            // Set positive button
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, id ->
            // User cancelled the dialog
            // Notify adapter about the item changed to redraw it on the screen
            binding.recyclerViewExercises.adapter?.notifyItemChanged(position)
        }

        builder.show()
    }


}