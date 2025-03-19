package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment private constructor(private val sides: Int = 6) : Fragment() {

    companion object {
        fun newInstance(sides: Int = 6) = DieFragment(sides)
    }

    val DIESIDE = "sidenumber"
    val PREVIOUS_ROLL = "previousroll"

    var currentRoll = 0
    lateinit var dieTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                currentRoll = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            throwDie()
        else {
            currentRoll = savedInstanceState.getInt(PREVIOUS_ROLL)
            dieTextView.text = currentRoll.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PREVIOUS_ROLL, currentRoll)
    }

    fun throwDie() {
        currentRoll = (Random.nextInt(sides) + 1)
        dieTextView.text = currentRoll.toString()
    }
}
