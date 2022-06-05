package com.example.tourismonmarslogbook.ui.annotationsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tourismonmarslogbook.databinding.FragmentAnnotationDetailBinding


class AnnotationDetailFragment : Fragment() {
    private var _binding: FragmentAnnotationDetailBinding? = null
    private val binding get() = _binding!!

    val args: AnnotationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnnotationDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = args.title
        val description = args.description
        binding.titleAnnotation.text = title
        binding.annotationDescription.text = description
    }

}