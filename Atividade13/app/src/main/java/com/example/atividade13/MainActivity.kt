package com.example.atividade13

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity() {
    val ID_REQUISICAO_FINE_LOCATION = 101
    lateinit var locationListener: LocationListener
    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnObter.setOnClickListener {
            requisitarLocalizacao()
        }

        btnParar.setOnClickListener {
            locationManager.removeUpdates(locationListener)
        }
    }

    fun requisitarLocalizacao() {
        val permissao = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissao == PackageManager.PERMISSION_GRANTED) {
            Log.d("Requisitar localização", "IF requisitarLocalizacao")
            val tempoAtualizacao: Long = 0
            val distanciaAtualizacao: Float = 0f

            // Obtendo dados de localização de rede
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                tempoAtualizacao, distanciaAtualizacao, locationListener)
        } else {
            Log.d("Requisitar localização", "ELSE requisitarLocalizacao")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
        permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == ID_REQUISICAO_FINE_LOCATION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                requisitarLocalizacao()
        } else {
            alert {
                message = "Permisssão não concedida"
                okButton {  }
            }.show()
        }
    }
}