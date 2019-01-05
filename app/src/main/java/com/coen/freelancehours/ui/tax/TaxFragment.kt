package com.coen.freelancehours.ui.tax

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.coen.freelancehours.R

class TaxFragment : Fragment() {
    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): TaxFragment {
            val fragmentHome = TaxFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tax, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var editTextHome = view.findViewById(R.id.editTextHome) as EditText
    }
}