package uz.example.apppinterest.ui.activity

import android.animation.Animator
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import uz.example.apppinterest.R
import uz.example.apppinterest.receiver.InternetBroadcastReceiver

class SplashActivity : AppCompatActivity() {

    private lateinit var linearInternet: LinearLayout
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        linearInternet = findViewById(R.id.linearInternet)
        lottieAnimationView = findViewById(R.id.lottie_layer_name)
        lottieAnimationView.playAnimation()

        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                val internetBroadcastReceiver = InternetBroadcastReceiver()
                val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

                internetBroadcastReceiver.onInternetOn = {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                internetBroadcastReceiver.onInternetOff = {
                    lottieAnimationView.visibility = View.GONE
                    linearInternet.visibility = View.VISIBLE

                }

                registerReceiver(internetBroadcastReceiver, intentFilter)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}
        })
    }
}