package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quiz.view.*

class QuizeAdapter(private val quizes: List<Quize>) :
    RecyclerView.Adapter<QuizeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quize: Quize) {
            itemView.tvQuizes.text = quize.quizeText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizeAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return quizes.size
    }

    override fun onBindViewHolder(holder: QuizeAdapter.ViewHolder, position: Int) {
        holder.bind(quizes[position])


    }


}