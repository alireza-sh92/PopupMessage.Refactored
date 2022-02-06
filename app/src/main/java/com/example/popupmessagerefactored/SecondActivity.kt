package com.example.popupmessagerefactored

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popupmessagerefactored.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val text = Intent()
            var inputted = binding.textEditor.editableText.toString()
            text.putExtra("Text", inputted)
            setResult(RESULT_OK, text)
            this.finish()
        }
    }
}