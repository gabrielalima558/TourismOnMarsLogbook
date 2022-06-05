package com.fiap.tourismonmarslogbook.ui.annotation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fiap.tourismonmarslogbook.database.Note
import com.fiap.tourismonmarslogbook.databinding.ListItemBinding

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffUtil) {

    var onItemClick: ((Note) -> Unit)? = null
    var secondNotesList: ArrayList<Note> = arrayListOf()

    object NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bindCell(note)
        secondNotesList.add(getItem(position))
    }

    fun getItemAt(position: Int): Note{
        return secondNotesList[position]
    }

    inner class NoteViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindCell(note: Note) {
                binding.txtTitle.text = note.title
                binding.txtDecription.text = note.description
            }
            init {
                binding.root.setOnClickListener {
                    onItemClick?.invoke(secondNotesList[adapterPosition])
                }
            }
        }
}