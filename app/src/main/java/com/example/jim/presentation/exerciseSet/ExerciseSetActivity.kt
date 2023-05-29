package com.example.jim.presentation.exerciseSet

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jim.R
import com.example.jim.databinding.ActivityExerciseSetBinding
import com.example.jim.data.model.Exercise
import com.example.jim.data.model.ExerciseSet
import com.example.jim.presentation.exerciseSet.viewmodel.ExerciseSetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseSetActivity : AppCompatActivity() {
    private val viewModel: ExerciseSetViewModel by viewModels()
    private lateinit var binding: ActivityExerciseSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exercise_set)


        val adapter = ExerciseSetAdapter()
        binding.recyclerViewExerciseSet.adapter = adapter
        binding.recyclerViewExerciseSet.layoutManager = LinearLayoutManager(this)


        val exercise = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("exercise", Exercise::class.java)
        } else {
            intent.getParcelableExtra<Exercise>("exercise")
        }
        val exerciseID = exercise!!.id


        viewModel.getSetsByExercise(exerciseID).observe(this, Observer { exerciseSet ->
            if (exerciseSet != null) {
                adapter.setExerciseSet(exerciseSet)
            }
        })


        // Swiping To Delete
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
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
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewExerciseSet)


        binding.addExerciseSet.setOnClickListener {
            Log.i("EXERCISE_ID", exerciseID.toString())
            val intent = Intent(this, AddExerciseSetActivity::class.java)
            intent.putExtra("EXERCISE_ID", exerciseID)
            startActivity(intent)
        }
    }


    fun onAlertDialog(item: ExerciseSet, view: View, position: Int) {
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
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show()
        }

        // Set positive button
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, id ->
            // User cancelled the dialog
            // Notify adapter about the item changed to redraw it on the screen
            binding.recyclerViewExerciseSet.adapter?.notifyItemChanged(position)
        }

        builder.show()
    }
}