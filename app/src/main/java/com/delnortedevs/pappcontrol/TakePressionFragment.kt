package com.delnortedevs.pappcontrol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*
import com.delnortedevs.pappcontrol.databinding.FragmentTakePressionBinding
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import kotlinx.coroutines.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TakePressionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TakePressionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentTakePressionBinding? = null
    private val binding get() = _binding!!

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTakePressionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fecha = "Hoy es: "
        val format = SimpleDateFormat("dd/MM/yyyy")
        val date = format.format(Date())
        fecha = fecha.plus(date)
        binding.tvFecha.text = fecha

        var sTriplicado = false
        binding.switchTriplicado.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sTriplicado = true
                binding.pSistolica1.visibility = View.VISIBLE
                binding.pDiastolica1.visibility = View.VISIBLE
                binding.pulso1.visibility = View.VISIBLE

                binding.pSistolica2.visibility = View.VISIBLE
                binding.pDiastolica2.visibility = View.VISIBLE
                binding.pulso2.visibility = View.VISIBLE
            }
            else {
                sTriplicado = false
                binding.pSistolica1.visibility = View.INVISIBLE
                binding.pDiastolica1.visibility = View.INVISIBLE
                binding.pulso1.visibility = View.INVISIBLE

                binding.pSistolica2.visibility = View.INVISIBLE
                binding.pDiastolica2.visibility = View.INVISIBLE
                binding.pulso2.visibility = View.INVISIBLE
            }
        }

        val db = Room.databaseBuilder(requireActivity().applicationContext,
            AppDatabase::class.java,
            "dbprofile").build()
        binding.btnGuardarPresion.setOnClickListener{
            //validaciones
            var s1 = !binding.pSistolica.text.isEmpty(); var d1 = !binding.pDiastolica.text.isEmpty(); var p1 = !binding.pulso.text.isEmpty()
            var s2 = !binding.pSistolica1.text.isEmpty(); var d2 = !binding.pDiastolica1.text.isEmpty(); var p2 = !binding.pulso1.text.isEmpty()
            var s3 = !binding.pSistolica2.text.isEmpty(); var d3 = !binding.pDiastolica2.text.isEmpty(); var p3 = !binding.pulso2.text.isEmpty()
            if(sTriplicado && s1 && s2 && s3 && d1 && d2 && d3 && p1 && p2 && p3){
                triplicado(db)
            }
            else if(!sTriplicado && s1 && d1 && p1) {
                normal(db)
            }
            else{
                Toast.makeText(activity, "Has olvidado algún campo. Intenta nuevamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun normal(db: AppDatabase) = runBlocking {
        lifecycleScope.launch{
            withContext(Dispatchers.IO){
                val sistolica = binding.pSistolica.text.toString().toInt()
                val diastolica = binding.pDiastolica.text.toString().toInt()
                val pulso = binding.pulso.text.toString().toInt()
                val presion = Presion(sistolica, diastolica, pulso)
                db.DaoPresion().addPresion(presion)
            }
        }
        Toast.makeText(activity, "¡Presión guardada correctamente!", Toast.LENGTH_SHORT).show()
        launch {
            delay(100L)
            findNavController().navigate(R.id.action_takePressionFragment_to_homePatientFragment)
        }
    }

    fun triplicado(db: AppDatabase) = runBlocking {
        lifecycleScope.launch{
            withContext(Dispatchers.IO){
                val sistolica = binding.pSistolica.text.toString().toInt()
                val diastolica = binding.pDiastolica.text.toString().toInt()
                val pulso = binding.pulso.text.toString().toInt()
                val presion = Presion(sistolica, diastolica, pulso)
                db.DaoPresion().addPresion(presion)

                val sistolica1 = binding.pSistolica1.text.toString().toInt()
                val diastolica1 = binding.pDiastolica1.text.toString().toInt()
                val pulso1 = binding.pulso1.text.toString().toInt()
                val presion1 = Presion(sistolica1, diastolica1, pulso1)
                db.DaoPresion().addPresion(presion1)

                val sistolica2 = binding.pSistolica2.text.toString().toInt()
                val diastolica2 = binding.pDiastolica2.text.toString().toInt()
                val pulso2 = binding.pulso2.text.toString().toInt()
                val presion2 = Presion(sistolica2, diastolica2, pulso2)
                db.DaoPresion().addPresion(presion2)
            }
        }
        Toast.makeText(activity, "¡Presión por triplicado guardada correctamente!", Toast.LENGTH_SHORT).show()
        launch {
            delay(100L)
            findNavController().navigate(R.id.action_takePressionFragment_to_homePatientFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TakePressionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TakePressionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}