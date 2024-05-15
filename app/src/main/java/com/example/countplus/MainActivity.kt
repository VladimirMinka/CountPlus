package com.example.countplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.countplus.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnPlus.setOnClickListener {
            viewModel.onClickPlus()
        }
        lifecycleScope.launch {
            viewModel.count.collect { count ->
                binding.count.text = count.toString()
            }
        }
        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                if (sideEffect == MainViewModel.SideEffect.TOAST) {
                    val textButton = "Увеличено " + viewModel.count.value
                    val toast = Toast.makeText(this@MainActivity, textButton, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
    }
}
