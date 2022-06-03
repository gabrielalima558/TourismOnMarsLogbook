package com.example.tourismonmarslogbook.ui.addannotation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tourismonmarslogbook.database.Note
import com.example.tourismonmarslogbook.database.NoteDatabase
import com.example.tourismonmarslogbook.databinding.FragmentAddAnnotationBinding

class AddAnnotationFragment : Fragment() {
    private var _binding: FragmentAddAnnotationBinding? = null
    private val binding get() = _binding!!

    private lateinit var addAnnotationViewModel: AddAnnotationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstace(application).noteDao
        val viewModelFactory = AddAnnotationViewModelFactory(dataSource)

        addAnnotationViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddAnnotationViewModel::class.java)

        _binding = FragmentAddAnnotationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addAnnotationButton.setOnClickListener {
            addNewAnnotation()
        }
        backButton()
        return view
    }

    private fun addNewAnnotation() {
        if (!binding.title.text.isNullOrEmpty() && !binding.editDescription.text.isNullOrEmpty()) {
            val note = Note(
                title = binding.title.text.toString(),
                description = binding.editDescription.text.toString()
            )
            addAnnotationViewModel.startInsertAnnotation(note)
            findNavController().navigate(AddAnnotationFragmentDirections.actionAddAnnotationFragmentToAnnotationFragment())

        } else {
            Toast.makeText(
                requireContext(),
                "Atenção tripulante, verifique se sua anotação tem título e descrição!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun backButton() {
       requireActivity().onBackPressedDispatcher.addCallback(this) {
           if (!binding.title.text.isNullOrEmpty() && !binding.editDescription.text.isNullOrEmpty()) {
               Toast.makeText(
                   requireContext(),
                   "Atenção tripulante, clique no botão de adicionar!",
                   Toast.LENGTH_LONG
               ).show()
           } else {
               findNavController().navigate(AddAnnotationFragmentDirections.actionAddAnnotationFragmentToAnnotationFragment())
           }
       }
    }

}