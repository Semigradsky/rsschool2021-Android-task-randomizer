package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {
    var navigator: Navigator? = null

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minValue: EditText? = null
    private var maxValue: EditText? = null

    private var min: Int? = null
    private var max: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        updateGenerateButtonAvailability()

        minValue?.doAfterTextChanged {
            min = minValue?.text.toString().toIntOrNull()
            updateGenerateButtonAvailability()
        }

        maxValue?.doAfterTextChanged {
            max = maxValue?.text.toString().toIntOrNull()
            updateGenerateButtonAvailability()
        }

        generateButton?.setOnClickListener {
            navigator?.openSecondFragment(min!!, max!!)
        }
    }

    private fun updateGenerateButtonAvailability() {
        generateButton?.isEnabled = isCorrectInput(min, max)
    }

    private fun isCorrectInput(min: Int?, max: Int?): Boolean {
        return min != null && max != null && min <= max
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}