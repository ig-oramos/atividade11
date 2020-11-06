package com.example.atividade7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eTxtHello = findViewById<TextView>(R.id.eTxtHello)
        val txtNome = findViewById<EditText>(R.id.txtNome)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)

        btnMostrar.setOnClickListener {
            if (txtNome.text.isEmpty())
                txtNome.error = "Este campo deve ser preenchido."
            else
                eTxtHello.text = "Hello, ${txtNome.text.toString()}!"
        }

        btnLimpar.setOnClickListener {
            txtNome.setText("")
        }


    }
}