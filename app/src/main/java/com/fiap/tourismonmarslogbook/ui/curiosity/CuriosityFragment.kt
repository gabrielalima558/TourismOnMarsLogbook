package com.fiap.tourismonmarslogbook.ui.curiosity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.tourismonmarslogbook.R
import com.fiap.tourismonmarslogbook.databinding.FragmentCuriosityBinding
import com.fiap.tourismonmarslogbook.model.Curiosity

class CuriosityFragment : Fragment() {
    private var _binding: FragmentCuriosityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCuriosityBinding.inflate(inflater, container, false)
        val view = binding.root
        createCuriosityList()
        return view
    }

    fun createCuriosityList() {
        val list = arrayListOf(
            Curiosity(
                requireActivity().getString(R.string.title_curiosity_one),
                requireActivity().getString(R.string.curiosity_one)
            ),
            Curiosity(
                requireActivity().getString(R.string.title_curiosity_two),
                requireActivity().getString(R.string.curiosity_two)
            ),
            Curiosity(
                requireActivity().getString(R.string.title_curiosity_tree),
                requireActivity().getString(R.string.curiosity_tree)
            )
        )
        binding.titleOne.text = list[0].title
        binding.curiosityOne.text = list[0].curiosity
        binding.titleTwo.text = list[1].title
        binding.curiosityTwo.text = list[1].curiosity
        binding.titleTree.text = list[2].title
        binding.curiosityTree.text = list[2].curiosity

    }

}