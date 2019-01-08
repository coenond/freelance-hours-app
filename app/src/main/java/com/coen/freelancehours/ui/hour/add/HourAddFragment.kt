package com.coen.freelancehours.ui.hour.add

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.coen.freelancehours.R
import com.coen.freelancehours.base.BaseFragment
import com.coen.freelancehours.databinding.FragmentHourAddBinding
import com.coen.freelancehours.model.Project
import com.coen.freelancehours.model.Tax
import com.coen.freelancehours.ui.hour.HourFragment
import kotlinx.android.synthetic.main.fragment_hour_add.*
import java.util.*
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.coen.freelancehours.ui.project.ProjectViewModel
import java.text.SimpleDateFormat


class HourAddFragment : BaseFragment<FragmentHourAddBinding, HourAddViewModel>() {

    private var calendar = Calendar.getInstance()
    private var clock = Calendar.getInstance()
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
    private val stf = SimpleDateFormat("HH:mm", Locale.GERMANY)
    override fun getVMClass(): Class<HourAddViewModel> = HourAddViewModel::class.java
    override fun initViewModelBinding() { binding.viewModel = viewModel }
    override fun getLayoutId(): Int = R.layout.fragment_hour_add

    /**
     * Initialize newInstance for passing paramters
     */
    companion object {
        fun newInstance(): HourAddFragment {
            val fragmentHome = HourAddFragment()
            val args = Bundle()
            fragmentHome.arguments = args

            return fragmentHome
        }
    }

    private fun initObservers() {
        viewModel.status.observe(this, Observer {
            sbMsg("Hour ${viewModel.name.value} added.")

            if (viewModel.status.value == "success") {
                val fragment = HourFragment.newInstance()
                addFragment(fragment)
            }
        })
    }

    var date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        et_date.setText(sdf.format(calendar.time).toString())
    }

    var timeStart = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
        clock.set(Calendar.HOUR_OF_DAY, hour)
        clock.set(Calendar.MINUTE, minute)

        et_started_at.setText(stf.format(clock.time).toString())
    }

    var timeFinished = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
        clock.set(Calendar.HOUR_OF_DAY, hour)
        clock.set(Calendar.MINUTE, minute)

        et_finished_at.setText(stf.format(clock.time).toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        val projects = arrayOf(Project(1, "Some Project", 25.0, 1, 1, 1, 1), Project(2, "Other Project", 25.0, 1, 1, 1, 1))
        val taxes = arrayOf(Tax(1, "BTW Hoog", 21.0), Tax(2, "BTW Laag", 9.0))

        val spinProjectsAdapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, projects)
        sp_project.adapter = spinProjectsAdapter

        val spinTaxesAdapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, taxes)
        sp_tax.adapter = spinTaxesAdapter

        et_date.setOnClickListener {
            DatePickerDialog(context, date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))
                    .show()
        }

        et_started_at.setOnClickListener {
            TimePickerDialog(context, timeStart,
                    clock.get(Calendar.HOUR_OF_DAY),
                    clock.get(Calendar.MINUTE), true)
                    .show()
        }

        et_finished_at.setOnClickListener {
            TimePickerDialog(context, timeFinished,
                    clock.get(Calendar.HOUR_OF_DAY),
                    clock.get(Calendar.MINUTE), true)
                    .show()
        }

        fab_submit.setOnClickListener {
            viewModel.project.value = sp_project.selectedItem as Project
            viewModel.tax.value = sp_tax.selectedItem as Tax
            viewModel.name.value = et_name.text.toString()
            viewModel.description.value = et_description.text.toString()
            viewModel.started_at.value = formatStartedAt()
            viewModel.finished_at.value = formatFinishedAt()

            viewModel.onSubmitClick()
        }
    }

    private fun formatStartedAt(): String {
        return "${et_date.text.toString()} ${et_started_at.text.toString()}:00"
    }

    private fun formatFinishedAt(): String {
        return "${et_date.text.toString()} ${et_finished_at.text.toString()}:00"
    }
}