package com.awul.roomdatabasecoroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.awul.roomdatabasecoroutine.databinding.ItemRecyclerHomeBinding
import com.awul.roomdatabasecoroutine.room.PersonModel

class PersonAdapter(var pList: ArrayList<PersonModel>): RecyclerView.Adapter<PersonAdapter.MyViewHolder>(){




    lateinit var onEditClick: (PersonModel) -> Unit
    lateinit var onDeleteClick: ((PersonModel) -> Unit)
    lateinit var onMoreClick: ((PersonModel) -> Unit)

    fun setOnEditClick(action: (PersonModel) -> Unit) {
                onEditClick = action
            }
    fun setonDeleteClick(action: (PersonModel) -> Unit) {
                onDeleteClick = action
            }
    fun setonMoreClick(action: (PersonModel) -> Unit) {
                onMoreClick = action
            }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding: ItemRecyclerHomeBinding =
            ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pItem = pList[position]
        val binding = holder.binding
        binding.tvName.text = pItem.name
        binding.tvMob.text = pItem.phone
    }


    inner class MyViewHolder(var binding: ItemRecyclerHomeBinding): ViewHolder(binding.root){
        init {
            binding.btnEdit.setOnClickListener {
                onEditClick(pList[adapterPosition])
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(pList[adapterPosition])
            }
            binding.btnMore.setOnClickListener {
                onMoreClick(pList[adapterPosition])
            }
        }

    }

}