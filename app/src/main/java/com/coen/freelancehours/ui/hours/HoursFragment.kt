package com.coen.freelancehours.ui.hours

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.coen.freelancehours.R
import com.coen.freelancehours.api.FreelanceHoursApiService
import com.coen.freelancehours.model.Project

class HoursFragment : Fragment() {

    lateinit var freelanceHoursApiService: FreelanceHoursApiService
    lateinit var project: Project

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): HoursFragment {
            val fragmentHome = HoursFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var editTextHome = view.findViewById(R.id.editTextHome) as EditText
    }
}