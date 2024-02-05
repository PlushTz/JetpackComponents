package com.example.lifecycle

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.lifecycle.databinding.ActivityAvdertisingBinding

class AdvertisingActivity : Activity(), LifecycleOwner {
    private val advertisingManage by lazy { AdvertisingManage() }
    private lateinit var binding: ActivityAvdertisingBinding
    private lateinit var lifecycleRegistry: LifecycleRegistry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvdertisingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycle.addObserver(advertisingManage)
        advertisingManage.advertisingManageListener = object : AdvertisingManage.AdvertisingManageListener {
            override fun timing(second: Int) {
                binding.tvCountDownTime.text = getString(R.string.count_down_time, second.toString())
            }

            override fun enterMainActivity() {
                Toast.makeText(this@AdvertisingActivity, "计时器完成", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    override fun onDestroy() {
        super.onDestroy()
    }
}