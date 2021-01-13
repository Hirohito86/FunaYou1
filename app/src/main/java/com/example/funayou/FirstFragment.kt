package com.example.funayou

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance()
    var email: String = ""
    var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mAuth: FirebaseAuth? = null
        // Inflate the layout for this fragment
        // (Aca solo se infla el layout especifico, nada de acciones o interacciones con la vista)
        return inflater.inflate(R.layout.fragment_first, container, false)
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

    // Una vez creada la vista e inflado el layout se puede interactuar,
    // para ello es el "onViewCreated" aca podemos hacer lo que queramos con la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById<Button>(R.id.log_button)?.setOnClickListener{
            loginUser()
        }
    }


    private fun loginUser() {
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            //Autenticamos con firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    view?.let { it1 -> Snackbar.make(it1, "holo", Snackbar.LENGTH_LONG).show()};

                }
                else{
                    view?.let { it1 -> Snackbar.make(it1, "", Snackbar.LENGTH_LONG).show()};

                }
            }
                .addOnCompleteListener() {
                    if(it.isSuccessful){
                        findNavController().navigate(R.id.action_FirstFragment_to_FifthFragment)
                        //equisdededede
                        //equisdededede

                    } else {
                     //   findNavController().navigate(R.id.action_FirstFragment_to_FifthFragment)
                        //val snackBar = Snackbar.make(
                            //activity!!.findViewById(android.R.id.content),
                            //"Look at me, I'm a fancy snackbar", Snackbar.LENGTH_LONG
                        //snackBar.show()
                    }
                }
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