package com.example.funayou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.ProgressBar as ProgressBar


class SecondFragment : AppCompatActivity() {

    private lateinit var NombreR:EditText
    private lateinit var ApellidoR:EditText
    private lateinit var EmailR:EditText
    private lateinit var PasswaordR:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_second)
        NombreR=findViewById(R.id.NombreR)
        ApellidoR=findViewById(R.id.ApellidoR)
        EmailR=findViewById(R.id.EmailR)
        PasswaordR=findViewById(R.id.PasswordR)

        progressBar= findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbReference=database.reference.child("User")
    }

    fun register(view: View) {
        createNewAccount()
    }
    private fun createNewAccount(){
        val name:String=NombreR.text.toString()
        val lastName:String=ApellidoR.text.toString()
        val email:String=EmailR.text.toString()
        val password:String=PasswaordR.text.toString()

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.VISIBLE

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userBD=dbReference.child(user?.uid.toString())

                        userBD.child("Name").setValue(name)
                        userBD.child("lastName").setValue(lastName)
                        action()
                    }
                }
        }
    }
    private fun action(){
        startActivity(Intent(this,FirstFragment::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?){
       user?.sendEmailVerification()
           ?.addOnCompleteListener(this){
               task ->

               if(task.isComplete){
                   Toast.makeText(this,"Email enviado",Toast.LENGTH_LONG).show()
               }else {
                   Toast.makeText(this,"Error al enviar el email",Toast.LENGTH_LONG).show()
               }
           }
    }

}


