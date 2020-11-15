package com.example.atividade12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.btnConvertAct
import kotlinx.android.synthetic.main.activity_menu.btnInfoAct
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnConvertAct.setOnClickListener {
            startActivity<ConvertActivity>()
        }

        btnInfoAct.setOnClickListener {
            startActivity<InfoActivity>()
        }
    }
}