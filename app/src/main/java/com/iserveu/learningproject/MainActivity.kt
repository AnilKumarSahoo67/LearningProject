package com.iserveu.learningproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.iserveu.learningproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        window.statusBarColor = 0xFF360040.toInt()

        val sampleAdapter = SampleAdapter()
        val sampleAdapter2 = SampleAdapter2()

        binding.recommendedRecyclerView.adapter = sampleAdapter

        val layoutManger = GridLayoutManager(this,2)
        binding.companyRecyclerview.layoutManager =layoutManger
        binding.companyRecyclerview.adapter =sampleAdapter2



        binding.popularBooksRecyclerview.adapter = sampleAdapter
        binding.comingSoonBooksRecyclerview.adapter = sampleAdapter

        binding.imageSlider.setOnClickListener{
            startActivity(Intent(this@MainActivity,MovieDetailsActivity::class.java))
        }



    }
}