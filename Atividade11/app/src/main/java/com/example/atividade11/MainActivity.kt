package com.example.atividade11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import org.w3c.dom.Text
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.nio.file.Files
import java.nio.file.Files.delete
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
            // Remover do bd
            database.use {
                if (delete("produto", "id = {id}",
                        "id" to item?.id.toString()) == 1) {
                    produtosAdapter.remove(item)
                    toast("Produto removido com sucesso.")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = lsvProdutos.adapter as ProdutoAdapter
        database.use {
            select("produto").exec {
                val parser = rowParser {id: Int, nome: String, quantidade: Int,
                    tipo_unidade: String, valor: Double, foto: ByteArray? ->
                    Produto(id, nome, quantidade, tipo_unidade, valor, foto?.toBitmap())
                }

                // Lista de produtos com dados do banco
                var listaProdutos = parseList(parser)
                // Limpando os dados da lista e carregando novas informações
                adapter.clear()
                adapter.addAll(listaProdutos)
                // Efetua a multiplicação e soma da quantidade e valor
                var soma = 0.0
                for (item in listaProdutos)
                    soma += item.valor * item.quantidade

                // Convertendo em formato de moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txtTotal.text = "Total: ${f.format(soma)}"
            }
        }
    }
}