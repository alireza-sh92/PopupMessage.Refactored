package com.example.popupmessagerefactored

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.popupmessagerefactored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Main Activity"
    }

    lateinit var binding: ActivityMainBinding
    lateinit var createCustomActivityresulLuncher: ActivityResultContract<Intent, String>
    lateinit var intentActivityResultLuncher: ActivityResultLauncher<Intent>
    lateinit var text: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createCustomActivityresulLuncher()
        createIntentActivityResultLuncher()
        var text: String
        binding.goToTextInserted.setOnClickListener {
            intentActivityResultLuncher.launch(Intent(this, SecondActivity::class.java))
                .toString()

        }

    }

    private fun createCustomActivityresulLuncher() {
        val contract = object : ActivityResultContract<String, String>() {
            override fun createIntent(context: Context, input: String?): Intent {
                return Intent(this@MainActivity, SecondActivity::class.java)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): String {
                return intent?.getStringExtra("Text") ?: "null"
            }

            val callback = ActivityResultCallback<String> {
                Log.d(TAG, "callback  $it")
            }


        }

    }

    private fun createIntentActivityResultLuncher() {
        var outPut: String
        intentActivityResultLuncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                outPut = it.data?.getStringExtra("Text").toString()
                if (it.resultCode == RESULT_OK && outPut.isNotEmpty()) {
                    Toast.makeText(this, outPut, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
