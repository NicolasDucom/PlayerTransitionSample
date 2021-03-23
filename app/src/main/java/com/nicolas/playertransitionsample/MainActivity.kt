package com.nicolas.playertransitionsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nicolas.playertransitionsample.databinding.ActivityMainBinding
import com.nicolas.playertransitionsample.utils.viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun updateProgress(progress:Float) {
        binding.mainMotion.progress = progress
    }
}