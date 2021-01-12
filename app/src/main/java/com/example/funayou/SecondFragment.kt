package com.example.funayou

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view?.findViewById<Button>(R.id.button_reg1)?.setOnClickListener{
            if (editTextTextPassword4.text.toString() == editTextTextPassword5.text.toString()){
            Datos.contraseña = editTextTextPassword4.text.toString()
            Datos.correo = editTextTextEmailAddress3.text.toString()
            }
            else if (!TextUtils.isEmpty(editTextTextPassword4.text.toString())|| !TextUtils.isEmpty(editTextTextEmailAddress3.text.toString()) || !TextUtils.isEmpty(editTextTextPassword5.text.toString())){
                //Dar mensaje de que tu vida está tan vacía como los textbox que no llenaste
            }
            else{
                //Dar mensaje de que t0do es un caos y todos moriremos
            }
            //Datos.foto =
            //Datos.nombre = ""
        }

    }
}