package com.example.atividade09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Criando o adaptador
        val produtosAdapter = ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1)

        val lsvProduto = findViewById<ListView>(R.id.lsvProduto)
        // Definindo o adapter na lista
        lsvProduto.adapter = produtosAdapter

        val btnInserir = findViewById<Button>(R.id.btnInserir)
        val txtProduto = findViewById<EditText>(R.id.txtProduto)
        val txtPreco = findViewById<EditText>(R.id.txtPreco)

        btnInserir.setOnClickListener {
            if (txtProduto.text.isEmpty())
                txtProduto.error = "Preencha este campo"
            else if (txtPreco.text.isEmpty())
                txtPreco.error = "Preencha este campo"
            else {
                val produto: Produto = Produto(txtProduto.text.toString(), txtPreco.text.toString().toDouble())
                produtosAdapter.add(produto)
            }
        }

        lsvProduto.setOnItemClickListener { adapterView: AdapterView<*>, view, position: Int, id: Long ->
            produtosAdapter.remove(produtosAdapter.getItem(position))            
        }        
    }
}
