package com.example.atividade11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import org.jetbrains.anko.startActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lsvProdutos = findViewById<ListView>(R.id.lsvProdutos)
        val produtosAdapter = ProdutoAdapter(this)
        lsvProdutos.adapter = produtosAdapter

        btnAdicionar.setOnClickListener {
            // Sem usar o anko
            // val intent = Intent(this, CadastroActivity::class.java)
            // startActivity(intent)
            // Utilizando a biblioteca anko
            startActivity<CadastroActivity>()
        }

        lsvProdutos.setOnItemClickListener { adapterView: AdapterView<*>, view, position: Int, id ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            produtosGlobal.remove(item)
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = lsvProdutos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        var soma = 0.0
        for (item in produtosGlobal)
            soma += item.valor * item.quantidade

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txtTotal.text = "Total: ${f.format(soma)}"
    }
}