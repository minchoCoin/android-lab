package com.example.stopwatch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.DecimalFormat
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.activityMain)

        val df00 = DecimalFormat("00")
        var time: LocalTime = LocalTime.of(0,0,0)
        var isPause = false
        var isStop = true
        val channel = Channel<Int>()
        var countingJob: Job? = null
        val backgroundScope = CoroutineScope(Dispatchers.Default + Job())

        var mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
                val hour = df00.format(it/3600)
                val min_sec = it%3600
                val minute = df00.format(min_sec/60)
                val second = df00.format(min_sec%60)
                binding.resultView.text = "$hour:$minute:$second"
            }
        }

        binding.startBut.setOnClickListener {
            /*
            1) start 기능 구현하기
            */
            if(isStop){
                isStop=false
                countingJob=backgroundScope.launch {
                    while(isActive){
                        try {
                            delay(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        time=time.plusSeconds(1)
                        channel.send(time.toSecondOfDay())
                    }
                }

            }
            else{
                Toast.makeText(this,"Stopwatch is not stopped now!!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.stopBut.setOnClickListener {
            /*
            2) stop 기능 구현하기
            */
            if(!isStop){
                val hour = df00.format(0)
                val min_sec = 0
                val minute = df00.format(0)
                val second = df00.format(0)
                binding.resultView.text = "$hour:$minute:$second"
                countingJob?.cancel()
                time=LocalTime.of(0,0,0)

                isStop=true

            }
        }

        binding.pauseBut.setOnClickListener {
            /*
            3) pause 기능 구현하기
            */
            if(!isPause){
                countingJob?.cancel()
                isPause=true
            }
        }

        binding.resumeBut.setOnClickListener {
            /*
            4) resume 기능 구현하기
            */
            if(isPause){
                countingJob=backgroundScope.launch {
                    while(isActive){
                        try {
                            delay(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        time=time.plusSeconds(1)
                        channel.send(time.toSecondOfDay())
                    }
                }
                isPause=false

            }
        }
    }
}