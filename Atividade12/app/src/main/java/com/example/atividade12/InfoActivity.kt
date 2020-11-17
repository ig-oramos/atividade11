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
//                long unixSeconds = 1372339860;
//// convert seconds to milliseconds
//                Date date = new java.util.Date(unixSeconds*1000L);
//// the format of your date
//                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//// give a timezone reference for formatting (see comment at the bottom)
//                sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
//                String formattedDate = sdf.format(date);
//                System.out.println(formattedDate);
                val unixSeconds: Long = dados.getInt("date").toLong()
                // Converte segundos em milissegundos
                val date: Date = Date(unixSeconds*1000L)
                // Configura o formato da data
                val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"))
                val formatedDate = sdf.format(date)

                txtDate.text = formatedDate

            }
        }
    }
}