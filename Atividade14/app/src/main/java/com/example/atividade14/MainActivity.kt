package com.example.atividade14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalcular.setOnClickListener {
            doAsync {
                if (txtCep.text.isNotEmpty()) {
                    val resposta = URL("https://viacep.com.br/ws/" +
                            txtCep.text.toString() + "/json/").readText()
                    val localizacao = JSONObject(resposta)
                    uiThread {
                        txtLogradouro.text = localizacao.getString("logradouro")
                        txtComplemento.text = localizacao.getString("complemento")
                        txtBairro.text = localizacao.getString("bairro")
                        txtLocalidade.text = localizacao.getString("localidade")
                        txtUf.text = localizacao.getString("uf")
                        txtDdd.text = localizacao.getInt("ddd").toString()
                    }
                } else
                    txtCep.error = "Campo obrigatório"
            }
        }
    }
}