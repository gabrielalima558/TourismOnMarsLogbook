package com.example.tourismonmarslogbook.ui.curiosity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tourismonmarslogbook.databinding.FragmentCuriosityBinding

class CuriosityFragment : Fragment() {
    private var _binding: FragmentCuriosityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCuriosityBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    fun createCuriosityList() {
        var list = arrayListOf<String>("Não existe som no espaço")
    }

}