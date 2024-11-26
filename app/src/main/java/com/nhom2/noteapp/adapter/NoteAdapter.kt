package com.nhom2.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nhom2.noteapp.databinding.NoteLayoutBinding
import com.nhom2.noteapp.fragments.HomeFragmentDirections
import com.nhom2.noteapp.model.Note
import com.nhom2.noteapp.R

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)
    private val differCallBack = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        // Giới hạn tiêu đề tối đa 50 ký tự
        val limitedTitle = if (currentNote.noteTitle.length > 50) {
            currentNote.noteTitle.substring(0, 50) + "..."
        } else {
            currentNote.noteTitle
        }

        // Giới hạn mô tả tối đa 200 ký tự
        val limitedDesc = if (currentNote.noteDesc.length > 200) {
            currentNote.noteDesc.substring(0, 200) + "..."
        } else {
            currentNote.noteDesc
        }
        val priority = currentNote.priority
        val noteDate = currentNote.noteDate
        when (currentNote.priority){
            "1" -> holder.itemBinding.noteDots.setBackgroundResource(R.drawable.red_dot)
            "2" -> holder.itemBinding.noteDots.setBackgroundResource(R.drawable.yellow_dot)
            "3" -> holder.itemBinding.noteDots.setBackgroundResource(R.drawable.green_dot)
            else -> holder.itemBinding.noteDots.setBackgroundResource(0)
        }
        holder.itemBinding.noteDate.text = noteDate
        holder.itemBinding.noteTitle.text = limitedTitle
        holder.itemBinding.noteDesc.text = limitedDesc

        // Điều hướng đến EditNoteFragment khi nhấn vào item
        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

}