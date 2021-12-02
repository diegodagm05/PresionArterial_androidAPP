package com.delnortedevs.pappcontrol

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.delnortedevs.pappcontrol.databinding.FragmentHomePatientBinding
import com.delnortedevs.pappcontrol.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val db = Room.databaseBuilder(requireActivity().applicationContext,
        AppDatabase::class.java,
        "dbprofile").build()

        lifecycleScope.launch {
            val prof = db.DaoPrincipal().getProfile()
            Log.d("DATABASEEEE", prof.toString())
            if(prof.isNotEmpty()){

                binding.editTextAge.setText(prof[0].edad.toString())
                binding.editTextNombre.setText(prof[0].nombre)
                binding.editTextCirc.setText(prof[0].circunferencia.toString())
                binding.editTextWeight.setText(prof[0].peso.toString())
                binding.editTextPhone.setText(prof[0].telefono)
                binding.editTextSex.setText(prof[0].sexo)


            }
            else{
                val prof = Profile("David", "8115822719", 21, 30.0,65.0,"Masculino")
                db.DaoPrincipal().addProfile(prof)
            }

        }

        binding.btnSaveProfile.setOnClickListener{

            lifecycleScope.launch{
                val queryRes = db.DaoPrincipal().getProfile()

                queryRes[0].edad = binding.editTextAge.text.toString().toInt()
                queryRes[0].nombre = binding.editTextNombre.text.toString()
                queryRes[0].circunferencia = binding.editTextCirc.text.toString().toDouble()
                queryRes[0].peso = binding.editTextWeight.text.toString().toDouble()
                queryRes[0].telefono = binding.editTextPhone.text.toString()
                queryRes[0].sexo = binding.editTextSex.text.toString()
                db.DaoPrincipal().updateProfile(queryRes[0])
            }
            Toast.makeText(activity, "Se ha guardado la nota de manera correcta", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}