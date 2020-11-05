package com.example.atividade11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_cadastro.*
import android.content.Intent
import android.app.Activity
import kotlinx.android.synthetic.main.list_view_item.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class CadastroActivity : AppCompatActivity() {
    val COD_IMAGE = 101
    var imageBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnInserir.setOnClickListener {
            if (txtCodigo.text.isEmpty())
                txtCodigo.error = "Este campo é obrigatório"
            else if (txtProduto.text.isEmpty())
                txtProduto.error = "Este campo é obrigatório"
            else if (txtValor.text.isEmpty())
                txtValor.error = "Este campo é obrigatório"
            else if (txtQtde.text.isEmpty())
                txtQtde.error = "Este campo é obrigatório"
            else if (txtTipoUnidade.text.isEmpty())
                txtTipoUnidade.error = "Este campo é obrigatório"
            else {
                txtCodigo.error = null
                txtProduto.error = null
                txtValor.error = null
                txtQtde.error = null
                txtTipoUnidade.error = null
                val produto: Produto = Produto(txtCodigo.text.toString().toInt(),
                    txtProduto.text.toString(), txtQtde.text.toString().toInt(),
                    txtTipoUnidade.text.toString(), txtValor.text.toString().toDouble())

                produtosGlobal.add(produto)
                txtCodigo.text.clear()
                txtProduto.text.clear()
                txtQtde.text.clear()
                txtTipoUnidade.text.clear()
                txtValor.text.clear()
            }
        }
    }

    fun abrirGaleria() {
        // Definindo a ação de conteúdo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definindo filtro para imagens
        intent.type = "image/"

        // Inicializando a activity com resultado
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }
}