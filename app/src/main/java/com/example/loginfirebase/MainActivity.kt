package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var botaoLogin : Button
    private lateinit var lembrarCheckBox: CheckBox
    private lateinit var cadastroTextView: TextView
    private lateinit var esqueciSenhaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.email)
        senhaEditText = findViewById(R.id.senha)
        botaoLogin = findViewById(R.id.btnLogin)
        lembrarCheckBox = findViewById(R.id.lembrar)
        cadastroTextView = findViewById(R.id.cadastro)
        esqueciSenhaTextView = findViewById(R.id.esqueciSenha)

        botaoLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val senha = senhaEditText.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                fazerLogin(email,senha)
            }
        }

        cadastroTextView.setOnClickListener{
            Toast.makeText(this, "Tela de cadastro ainda não implementada", Toast.LENGTH_SHORT).show()
        }
        esqueciSenhaTextView.setOnClickListener {
            Toast.makeText(this,"Função de recuperação ainda não implementada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fazerLogin(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, TelaInicialActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}