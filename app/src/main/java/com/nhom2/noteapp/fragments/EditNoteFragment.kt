package com.nhom2.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.app.AlertDialog
import android.text.format.DateFormat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.nhom2.noteapp.MainActivity
import com.nhom2.noteapp.R
import com.nhom2.noteapp.databinding.FragmentEditNoteBinding
import com.nhom2.noteapp.model.Note
import com.nhom2.noteapp.viewmodel.NoteViewModel
import java.util.Date


class EditNoteFragment : Fragment(R.layout.fragment_edit_note), MenuProvider {

    private var editNoteBinding: FragmentEditNoteBinding? = null
    private val binding get() = editNoteBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote:Note

    private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!
        var priority: String = currentNote.priority
        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        // Thiết lập trạng thái nút ưu tiên đã lưu
        when (currentNote.priority){
            "1" -> {
                binding.pRed.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "2" -> {
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
        }
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
        binding.editNoteFab.setOnClickListener{
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()
            val d = Date()
            val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

            if(noteTitle.isNotEmpty()){
                val note = Note(currentNote.id, noteTitle, noteDesc, noteDate.toString().trim(), priority)
                notesViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment, false)
            }else{
                Toast.makeText(context, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun deleteNote(){

        AlertDialog.Builder(activity).apply {
            setTitle("Xoá ghi chú")
            setMessage("Bạn có chắc muốn xóa ghi chú này không ?")
            setPositiveButton("Xóa"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Đã xóa ghi chú!", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Đóng", null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteNote()
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

}