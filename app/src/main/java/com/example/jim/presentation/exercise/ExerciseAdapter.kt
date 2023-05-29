package com.example.jim.presentation.exercise

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jim.R
import com.example.jim.data.model.Exercise

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    private var exercises = emptyList<Exercise>()
    var onItemClick: ((Exercise) -> Unit)? = null


    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExercise: TextView = itemView.findViewById(R.id.text_view_exercise)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exercises[position]
        holder.textViewExercise.text = currentExercise.name
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentExercise)
        }
    }


    override fun getItemCount() = exercises.size


    @SuppressLint("NotifyDataSetChanged")
    fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }


    fun getExerciseAt(position: Int): Exercise {
        return exercises[position]
    }
}
