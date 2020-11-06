package com.example.atividade08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spnSexo = findViewById<Spinner>(R.id.spnSexo)
        val txtIdade = findViewById<TextView>(R.id.txtIdade)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        spnSexo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                listOf("Masculino","Feminino"))

        // Criterios: 65 homens, 62 mulheres

        //btn_Calcular_onclick
        btnCalcular.setOnClickListener {
            val sexo = spnSexo.selectedItem as String
            if (txtIdade.text.isEmpty())
                txtIdade.error = "Este campo precisa ser preenchido."
            else if (txtContrib.text.isEmpty())
                txtContrib.error = "Este campo precisa ser preenchido."
            else {
                val idade: Int = txtIdade.text.toString().toInt()
                val contrib: Int = txtContrib.text.toString().toInt()
                var resultado = 0
                if (sexo == "Masculino")
                    resultado = 65 - (idade + contrib)
                else
                    resultado = 62 - (idade + contrib)
                if (resultado < 0)
                    txtResultado.text = "Você já cumpriu o tempo necessário."
                else
                    txtResultado.text = "Faltam $resultado anos para você se aposentar."
            }
        }
    }
}