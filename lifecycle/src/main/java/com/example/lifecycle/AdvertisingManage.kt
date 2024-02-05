package com.example.lifecycle

import android.os.CountDownTimer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Desc:
 * @author lijt
 * Created on 2024/2/5
 * Email: lijt@eetrust.com
 */
class AdvertisingManage : DefaultLifecycleObserver {
    var advertisingManageListener: AdvertisingManageListener? = null

    /**
     * 定时器
     */
    private var countDownTimer: CountDownTimer? = object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            advertisingManageListener?.timing((millisUntilFinished / 1000).toInt())
        }

        override fun onFinish() {
            advertisingManageListener?.enterMainActivity()
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        start()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        cancel()
    }

    private fun start() {
        countDownTimer?.start()
    }

    private fun cancel() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    interface AdvertisingManageListener {
        /**
         * 记时
         * @param second Int
         */
        fun timing(second: Int)

        /**
         * 进入MainActivity
         */
        fun enterMainActivity()
    }
}