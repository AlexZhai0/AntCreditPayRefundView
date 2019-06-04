package com.alex.antcreditpayrefundview

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        animateView()
    }

    private fun initRecyclerView() {
        val listData = mutableListOf<String>()
        for (i in 1..50) {
            listData.add("列 $i")
        }
        val adapter = SampleListAdapter(listData)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun animateView() {
        downView.setOnClickListener {
            ObjectAnimator.ofFloat(downView, "translationY", 100f).apply {
                duration = 1000
                start()
            }
        }
        rlBack.setOnClickListener {
            ObjectAnimator.ofFloat(downView, "translationY", 0f).apply {
                duration = 1000
                start()
            }
        }
    }
}


class SampleListAdapter(private val list: MutableList<String>): RecyclerView.Adapter<SampleListAdapter.SampleListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListHolder {
        return SampleListHolder(
            LayoutInflater.from(parent.context).inflate(
                android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SampleListHolder, position: Int) {
        holder.textView.text = list[position]
        holder.textView.setOnClickListener {
            Toast.makeText(holder.textView.context, list[position], Toast.LENGTH_SHORT).show()
        }
    }

    class SampleListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView as TextView
    }
}
