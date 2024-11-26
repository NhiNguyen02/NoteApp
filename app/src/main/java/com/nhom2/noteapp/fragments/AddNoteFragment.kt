package com.nhom2.noteapp.fragments

import android.text.format.DateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.nhom2.noteapp.MainActivity
import com.nhom2.noteapp.R
import com.nhom2.noteapp.databinding.FragmentAddNoteBinding
import com.nhom2.noteapp.model.Note
import com.nhom2.noteapp.viewmodel.NoteViewModel
import java.util.Date


class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {

    private var addNoteBinding:FragmentAddNoteBinding? = null
    private val binding get() = addNoteBinding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNoteView: View
    private var priority: String ="1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       addNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view
        // Thiết lập các sự kiện click
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "1"
        }

        binding.pYellow.setOnClickListener {
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "2"
        }

        binding.pGreen.setOnClickListener {
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
            priority = "3"
        }
    }

    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        if(noteTitle.isNotEmpty()){
            val note = Note(0, noteTitle, noteDesc, noteDate.toString().trim(),priority)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context, "Đã Lưu", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
        } else{
            Toast.makeText(addNoteView.context, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu ->{
                saveNote(addNoteView)
                true
            }else ->false

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding = null
    }
}