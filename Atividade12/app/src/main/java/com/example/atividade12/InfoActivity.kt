package com.example.atividade12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.sql.Timestamp
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        buscarDados()
    }

    fun buscarDados() {
        doAsync {
            val resposta = URL(API_URL).readText()
            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            uiThread {
                val dados = JSONObject(resposta).getJSONObject("ticker")
                txtBuy.text = f.format(dados.getDouble("buy"))
                txtSell.text = f.format(dados.getDouble("sell"))
                txtHigh.text = f.format(dados.getDouble("high"))
                txtLow.text = f.format(dados.getDouble("low"))

                val unixSeconds: Long = dados.getInt("date").toLong()
                // Converte segundos em milissegundos
                val date: Date = Date(unixSeconds*1000L)
                // Configura o formato da data
                val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                // Adiciona uma refêrencia de zona de tempo
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"))
                val formatedDate = sdf.format(date)

                txtDate.text = formatedDate

            }
        }
    }
}
