package com.example.atividade11

import android.graphics.Bitmap

data class Produto(val codigo: Int, val nome: String, val quantidade: Int, val tipoUnidade: String,
    val valor: Double, val foto: Bitmap? = null)

