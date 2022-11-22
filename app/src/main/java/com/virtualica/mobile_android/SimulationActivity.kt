package com.virtualica.mobile_android

import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_simulation.*

class SimulationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        time()
    }


    private fun time(){
        val timeInitial = (12).toLong()
        object : CountDownTimer(timeInitial, 1000){

            override fun onFinish() {
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                val r = RingtoneManager.getRingtone(this@SimulationActivity, notification)
                r.play()
                timeFinished("El tiempo se ha acabado", "Se ha acabado el tiempo para responder las preguntas", r)
            }

            override fun onTick(p0: Long) {
                val timeRes=(p0/1000).toInt()+1
                val minutes = (timeRes/60).toInt()
                val secondRes = timeRes - (minutes*60)
                txtTime.text="Tiempo restante: ${minutes}:${secondRes} minutos"
            }
        }.start()
    }

    private fun timeFinished(msg:String, des:String, r : Ringtone){
        MaterialAlertDialogBuilder(this)
            .setTitle(msg)
            .setMessage(des)
            .setPositiveButton("OK") { dialog, which ->
                r.stop()
                finish()
            }
            .show()

    }
}