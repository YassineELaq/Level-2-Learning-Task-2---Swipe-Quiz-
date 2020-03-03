package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizes = arrayListOf<Quize>()


    private val quizeAdapter = QuizeAdapter(quizes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        quizes.add(Quize("A 'val' and 'var' are the same.", false))
        quizes.add(Quize("Mobile Apllication Development grants 12 ECTS.", false))
        quizes.add(Quize("In Kotlin 'when' replaces the 'switch' operator in Java.", true))
        quizes.add(Quize("Does Kotlin support primitive Datatypes?", false))
        quizes.add(Quize("Is there something called init block in Kotlin?", true))
        quizes.add(Quize("Is there any dependency of Secondary Constructors on Primary Constructors?", true))
        initViews()

    }

    private fun initViews() {
        tvQuizes.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        tvQuizes.adapter = quizeAdapter
        tvQuizes.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(tvQuizes)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.


        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        if (!quizes.get(position).isCorrect) {
                            quizes.removeAt(position)
                            quizeAdapter.notifyDataSetChanged()
                            onAnswerCorrect()
                        }else{
                            onAnswerIncorrect()
                            quizeAdapter.notifyDataSetChanged()
                        }
                    }
                    ItemTouchHelper.RIGHT -> {
                        if (quizes.get(position).isCorrect) {
                            quizes.removeAt(position)
                            quizeAdapter.notifyDataSetChanged()
                            onAnswerCorrect()
                        }else{
                            onAnswerIncorrect()
                            quizeAdapter.notifyDataSetChanged()

                        }
                    }
                    else -> {
                        errorText()
                    }
                }


            }
        }
        return ItemTouchHelper(callback)
    }

    /**
     * Displays a successful Toast message.
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()

    }

    /**
     * Displays a incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()

    }
    private fun errorText() {
        Toast.makeText(this, getString(R.string.errorText), Toast.LENGTH_SHORT).show()

    }


}
