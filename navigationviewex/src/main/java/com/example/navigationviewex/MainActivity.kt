package com.example.navigationviewex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val naviItem = findViewById<NavigationView>(R.id.main_drawer_view)
        naviItem.setNavigationItemSelectedListener {
            Log.d("test", "${it.title} 선택!!")
            true
        }

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.extendedFab)
        fab.setOnClickListener{
            when(fab.isExtended){ // 플로팅 버튼 확장 상태인지 체크
                true -> fab.shrink() // 확장 상태면 축소
                false -> fab.extend() // 축소 상태이면 확장
            }
        }
    }
}