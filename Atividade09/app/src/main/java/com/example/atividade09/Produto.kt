package com.example.atividade09

data class Produto(val nome: String, val preco: Double) {
    override fun toString(): String {
        return "Nome: $nome, preço: R$ $preco"
    }
}
