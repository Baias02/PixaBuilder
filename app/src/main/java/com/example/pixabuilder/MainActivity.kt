package com.example.pixabuilder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabuilder.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter = PixaAdapter()
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        initClick()
        Scrolled()
    }

    private fun initClick() {
        with(binding) {
            getBtn.setOnClickListener {
                adapter.list.clear()
                searchImage()
            }
            povBtn.setOnClickListener {
                page++
                searchImage()
            }
        }
    }

    private fun searchImage() {
        RetrofitService.api.searchImage(binding.searchEdit.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        val result = response.body()?.hits
                        adapter.addImage(result!!)
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun Scrolled() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        val scrollListener = object : Scrolled(layoutManager) {
            override fun onLoadMore() {
                page++
                searchImage()
            }
        }

        binding.recyclerView.addOnScrollListener(scrollListener)

    }
}