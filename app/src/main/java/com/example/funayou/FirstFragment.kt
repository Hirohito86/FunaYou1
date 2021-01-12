package com.example.funayou

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_seventh.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mAuth: FirebaseAuth? = null
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)






        view?.findViewById<Button>(R.id.log_button)?.setOnClickListener{
            loginUser()
        }

        /*
        view?.findViewById<Button>(R.id.reg_button)?.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()

            if (fragment.isAdded) {
                transaction
                    .hide(currentFragment)
                    .show(fragment)
            } else {
                transaction
                    .hide(currentFragment)
                    .add(R.id.container, fragment, tag)
            }

            transaction.commit()
        }

*/
    }

        var mAuth = FirebaseAuth.getInstance()
        var email: String = ""
        var password: String = ""

    private fun loginUser() {
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {


            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() {
                        task ->
                    if (task.isSuccessful) {
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        transaction?.replace(R.id.nav_host_fragment, FourthFragment())
                        transaction?.disallowAddToBackStack()
                        transaction?.commit()
                        //equisdededede

                        } else {
                        // sino le avisamos al usuario que orcurrio un problema
                        //Toast.makeText(this, "Authentication failed.",
                            //Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            //Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }





/*
    private fun registerUser(){

        //Obtenemos los datos de nuestras cajas de texto
        firstName = txtName.text.toString()
        lastName = txtLastName.text.toString()
        email = txtEmail.text.toString()
        password = txtPassword.text.toString()

//Verificamos que los campos estén llenos
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

/*Antes de iniciar nuestro registro bloqueamos la pantalla o también podemos usar una barra de proceso por lo que progressbar está obsoleto*/

            progressBar.setMessage("Usuario registrado...")
            progressBar.show()

//vamos a dar de alta el usuario con el correo y la contraseña
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {

//Si está en este método quiere decir que todo salio bien en la autenticación

/*Una vez que se dio de alta la cuenta vamos a dar de alta la información en la base de datos*/

/*Vamos a obtener el id del usuario con que accedio con currentUser*/
                    val user:FirebaseUser = auth.currentUser!!
//enviamos email de verificación a la cuenta del usuario
                    verifyEmail(user);
/*Damos de alta la información del usuario enviamos el la referencia para guardarlo en la base de datos  de preferencia enviamos el id para que no se repita*/
                    val currentUserDb = databaseReference.child(user.uid)
//Agregamos el nombre y el apellido dentro de user/id/
                    currentUserDb.child("firstName").setValue(firstName)
                    currentUserDb.child("lastName").setValue(lastName)
//Por último nos vamos a la vista home
                    updateUserInfoAndGoHome()

                }.addOnFailureListener{
// si el registro falla se mostrara este mensaje
                    Toast.makeText(this, "Error en la autenticación.",
                        Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
*/

}