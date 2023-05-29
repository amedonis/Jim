package com.example.jim.presentation.exerciseSet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jim.R
import com.example.jim.data.model.ExerciseSet

class ExerciseSetAdapter : RecyclerView.Adapter<ExerciseSetAdapter.ExerciseSetViewHolder>() {


    private var exerciseSet = emptyList<ExerciseSet>()
    var onItemClick: ((ExerciseSet) -> Unit)? = null


    inner class ExerciseSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExerciseSet: TextView = itemView.findViewById(R.id.text_view_exercise_set)
        val textViewWeight: TextView = itemView.findViewById(R.id.text_view_weight)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseSetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_set_item, parent, false)
        return ExerciseSetViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExerciseSetViewHolder, position: Int) {
        val currentExerciseSet = exerciseSet[position]
        holder.textViewExerciseSet.text = currentExerciseSet.setNumber.toString()
        holder.textViewWeight.text = currentExerciseSet.weight.toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentExerciseSet)
        }
    }


    override fun getItemCount() = exerciseSet.size


    @SuppressLint("NotifyDataSetChanged")
    fun setExerciseSet(exerciseSet: List<ExerciseSet>) {
        this.exerciseSet = exerciseSet
        notifyDataSetChanged()
    }


    fun getExerciseAt(position: Int): ExerciseSet {
        return exerciseSet[position]
    }
}