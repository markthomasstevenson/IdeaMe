package uk.co.markthomasstevenson.ideame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(R.color.gram_hair)
    }

    fun setStatusBar(colour: Int) {
        window.statusBarColor = ContextCompat.getColor(applicationContext, colour)
    }
}