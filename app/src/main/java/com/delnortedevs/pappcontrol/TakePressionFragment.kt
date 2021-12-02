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

        binding.btnGuardarPresion.setOnClickListener{
            if(sTriplicado){
                //pasar valores para el triplicado
                Toast.makeText(activity, "¡Presión por triplicado guardada correctamente!", Toast.LENGTH_SHORT).show()
            }
            else{
                //pasar valores para 1 toma de presion
                Toast.makeText(activity, "¡Presión guardada correctamente!", Toast.LENGTH_SHORT).show()
            }
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