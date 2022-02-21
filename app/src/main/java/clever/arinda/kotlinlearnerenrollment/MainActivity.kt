package clever.arinda.kotlinlearnerenrollment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import clever.arinda.kotlinlearnerenrollment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
    }
}