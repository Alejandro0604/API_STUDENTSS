package com.example.api_students

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.api_students.data.APIService
import com.example.api_students.data.RetrofitBuilder
import com.example.api_students.data.models.Data
import com.example.api_students.databinding.FragmentCarreraBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("UNREACHABLE_CODE")
class CarreraFragment : Fragment() {

        private lateinit var binding: FragmentCarreraBinding


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentCarreraBinding.inflate(inflater, container, false)
            initCarrera()
            return binding.root

            binding.btnGuardarCa.setOnClickListener {
                findNavController().navigate(R.id.btnCarrera)
          }
        }

        private fun initCarrera() {
            binding.btnGuardarCa.setOnClickListener {
                saveCarrera()
            }
        }

        private fun saveCarrera() {
            val context = requireContext()
            CoroutineScope(Dispatchers.IO).launch {
                val carrera = Data(
                    id = 0,
                    nombre = binding.tfNombreCarrera.editText?.text?.trim().toString(),
                    image = binding.tfURLImageEd.editText?.text?.trim().toString(),
                    created_at = Date().toString(),
                    updated_at = Date().toString()
                )
                val retrofit = RetrofitBuilder.getRetrofit()
                val call = retrofit.create(APIService::class.java).saveCarrera("carreras", carrera)
                val response = call.body()
                requireActivity().runOnUiThread {
                    if (call.isSuccessful && response != null) {
                        Toast.makeText(context, "Registro guardado...", Toast.LENGTH_LONG).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(context, "Error al guardar...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
