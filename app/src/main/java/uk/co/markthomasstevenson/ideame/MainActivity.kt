package uk.co.markthomasstevenson.ideame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var plusToTick: AnimatedVectorDrawableCompat? = null
    private var tickToPlus: AnimatedVectorDrawableCompat? = null
    private var showingPlus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusToTick = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.plustotick)
        tickToPlus = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.ticktoplus)
        fab.setImageDrawable(plusToTick)
        fab.setOnClickListener{ morph() }
        showingPlus = true
    }

    fun morph() {
        val drawable = if (showingPlus) plusToTick else tickToPlus
        fab.setImageDrawable(drawable)
        drawable?.start()
        showingPlus = !showingPlus
    }
}
