package com.example.api_students

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.api_students.databinding.FragmentHomeBinding


class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnEstudiante.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_homeEstudianteFragment)
        }

        binding.btnCarrera.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_homeCarreraFragment)
        }
        return binding.root
    }
}