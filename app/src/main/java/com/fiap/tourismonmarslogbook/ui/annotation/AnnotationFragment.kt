package com.fiap.tourismonmarslogbook.ui.annotation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiap.tourismonmarslogbook.R
import com.fiap.tourismonmarslogbook.database.NoteDatabase
import com.fiap.tourismonmarslogbook.databinding.FragmentAnnotationBinding
import com.fiap.tourismonmarslogbook.ui.annotation.adapter.NoteAdapter

class AnnotationFragment : Fragment() {
    private var _binding: FragmentAnnotationBinding? = null
    private val binding get() = _binding!!

    private lateinit var annotationViewModel: AnnotationViewModel
    private val adapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        backButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstace(application).noteDao
        val viewModelFactory = AnnotationViewModelFactory(dataSource)
        annotationViewModel =
            ViewModelProvider(this, viewModelFactory).get(AnnotationViewModel::class.java)

        _binding = FragmentAnnotationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addAnotation.setOnClickListener {
            findNavController().navigate(AnnotationFragmentDirections.actionAnnotationFragmentToAddAnnotationFragment())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        binding.anotationsList.adapter = adapter
        binding.anotationsList.addItemDecoration(
            DividerItemDecoration(
                this.activity,
                LinearLayoutManager.VERTICAL
            )
        )
        annotationViewModel.notes().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                Toast.makeText(activity, "Tripulante faça suas anotações!", Toast.LENGTH_LONG)
                    .show()
            }
        }
       swipeToDelete()
    }
    private fun swipeToDelete() {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    annotationViewModel.startDeleteAnnotation(adapter.getItemAt(viewHolder.adapterPosition))
                    annotationViewModel.notes()

                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.anotationsList)

        adapter.onItemClick = { note ->
            findNavController().navigate(
                AnnotationFragmentDirections.actionAnnotationFragmentToAnotationDetailFragment(
                    note.title,
                    note.description
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(AnnotationFragmentDirections.actionAnnotationFragmentToRulesFragment())
                true
            }
            else -> false
        }

    }
    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

}