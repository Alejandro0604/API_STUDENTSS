package com.example.api_students

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_students.data.APIServiceStudent
import com.example.api_students.data.RetrofitBuilder
import com.example.api_students.data.adapter.EstudianteAdapter
import com.example.api_students.data.models.DataEstudiante
import com.example.api_students.databinding.FragmentHomeEstudianteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeEstudianteFragment : Fragment() {
    private lateinit var binding: FragmentHomeEstudianteBinding
    private lateinit var adapter: EstudianteAdapter
    private val listadoEs = mutableListOf<DataEstudiante>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeEstudianteBinding.inflate(inflater, container, false)


        initViewEs()
        initRecyclerViewEs()
        showEstudiantes()

        return binding.root
    }

    private fun initViewEs() {
        binding.btnAAdirEstudiante.setOnClickListener {
            goNewEstudiante()
        }
        binding.tfBuscarEstudiante.setOnClickListener {
            val query = binding.etBuscarEstudiante.text.toString().trim()
            searchEstudiantes(query)
        }
    }

    private fun initRecyclerViewEs() {
        adapter = EstudianteAdapter(listadoEs, this::onEditClickEs, this::onDeleteClickEs)
        binding.RvListaEstudiante.layoutManager = LinearLayoutManager(context)
        binding.RvListaEstudiante.adapter = adapter
    }

    private fun onDeleteClickEs(data: DataEstudiante) {
        val context = requireContext()


        lifecycleScope.launch {
            try {
                val retrofit = RetrofitBuilder.getRetrofit()
                val call = retrofit.create(APIServiceStudent::class.java).deleteEstudiante("estudiantes", data.id)
                val response = call.body()
                requireActivity().runOnUiThread {
                    if (call.isSuccessful) {
                        listadoEs.remove(data)
                        adapter.notifyDataSetChanged()
                    } else Toast.makeText(context, "Error al eliminar", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Registro eliminado...", Toast.LENGTH_LONG).show()
                }
            } catch (ex: Exception) {
                Log.e("error", ex.toString())
                Toast.makeText(context, "error ${ex.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onEditClickEs(data: DataEstudiante) {
        val bundle = Bundle()
        bundle.putInt("estudiante_id", data.id)
        findNavController().navigate(R.id.action_homeEstudianteFragment_to_estudianteFragmentEdit)
    }

    private fun showEstudiantes() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitBuilder.getRetrofit()
            val call = retrofit.create(APIServiceStudent::class.java).listaEstudiantes("estudiantes")
            val response = call.body()
            requireActivity().runOnUiThread {
                if (call.isSuccessful && response != null) {
                    val listaEstudiantes = response.data.toMutableList()
                    listadoEs.clear()
                    listadoEs.addAll(listaEstudiantes)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("listaStudents", "Hubo un error")
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
    }

    private fun goNewEstudiante() {
        findNavController().navigate(R.id.action_homeEstudianteFragment_to_estudianteFragment)
    }

    private fun searchEstudiantes(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retrofit = RetrofitBuilder.getRetrofit()
                val call = retrofit.create(APIServiceStudent::class.java).listaEstudiantes("estudiantes", query)
                val response = call.body()
                requireActivity().runOnUiThread {
                    if (call.isSuccessful && response != null) {
                        Log.e("ListaStudents", "ok")
                        val listaEstudiantes = response.data.toMutableList()
                        listadoEs.clear()
                        listadoEs.addAll(listaEstudiantes)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("ListaStudents", "Hubo un error")
                        showError()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(context, "Failure: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}