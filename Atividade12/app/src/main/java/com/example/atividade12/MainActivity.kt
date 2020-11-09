package com.example.atividade12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.net.URL
import android.util.Log
import kotlinx.android.synthetic.main.bloco_cotacao.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val TAG = "Marcos"
    val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buscarCotacao()
        Log.v(TAG, "log de verbose")
        Log.d(TAG, "log de debug")
        Log.i(TAG, "log de info")
        Log.w(TAG, "log de alerta")
        Log.e(TAG, "log de erro", RuntimeException("teste de erro"))
    }

    fun buscarCotacao() {
        doAsync {
            // Acessa a API e busca seu resultado
            val resposta = URL(API_URL).readText()
            val cotacaoBitcoin = JSONObject(resposta).getJSONObject(
                "ticker").getDouble("last")
            uiThread {
                txtCotacao.setText("$cotacaoBitcoin")
            }
        }
    }
}