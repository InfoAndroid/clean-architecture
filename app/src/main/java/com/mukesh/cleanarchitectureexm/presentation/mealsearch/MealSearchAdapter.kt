package com.mukesh.cleanarchitectureexm.presentation.mealsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mukesh.cleanarchitectureexm.databinding.ViewHolderSerachListBinding
import com.mukesh.cleanarchitectureexm.domain.model.Meal

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.MyViewHolder>() {

    private var listener: ((Meal) -> Unit)? = null

    var list = mutableListOf<Meal>()

    fun setContentList(list: MutableList<Meal>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ViewHolderSerachListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealSearchAdapter.MyViewHolder {
        val binding =
            ViewHolderSerachListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l: (Meal) -> Unit) {
        listener = l
    }

    override fun onBindViewHolder(holder: MealSearchAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.meal = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}