package jimmys.tip.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import jimmys.tip.calculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        val costOfServiceValue = binding.costOfService.text

        if (costOfServiceValue.isNullOrBlank()) {
            binding.costOfService.error = "Please enter value!"
        } else {

            val selectedId = binding.tipOption.checkedRadioButtonId

            val userEnterPer = binding.userWantPercent.text

            if (selectedId == R.id.option_user_set) {
                if (userEnterPer.isNullOrBlank()) {
                    binding.userWantPercent.error = "Please enter value!"
                    return
                }
            }

            val tipPercentage = when (selectedId) {
                R.id.option_twenty_precent -> 0.20
                R.id.option_eighteen_precent -> 0.18
                R.id.option_fifthteen_precent -> 0.15
                R.id.option_user_set -> userEnterPer.toString().toDouble() / 100
                else -> {
                    Toast.makeText(this, "Please select service option below.", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            var tip = tipPercentage * costOfServiceValue.toString().toDouble()
            val roundUp = binding.roundUpSwitch.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        }

    }
}