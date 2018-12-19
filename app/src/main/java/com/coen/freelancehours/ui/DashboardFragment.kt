package com.coen.freelancehours.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.coen.freelancehours.R
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.FreelanceHoursApiService
import com.coen.freelancehours.api.response.ProjectSingleResponse
import com.coen.freelancehours.model.Project
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    lateinit var freelanceHoursApiService: FreelanceHoursApiService
    lateinit var project: Project

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): DashboardFragment {
            val fragmentHome = DashboardFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var editTextHome = view.findViewById(R.id.editTextHome) as EditText

        // Start API Service
        freelanceHoursApiService = FreelanceHoursApi.start()
        getProject()
    }

    private fun getProject() {
        Log.i("TAGZ", "GET_PROJECT")
        freelanceHoursApiService.getProject("2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectSingleResponse> {
                    override fun onSuccess(response: ProjectSingleResponse) {
                        project = response.project
                        sbMsg(project.toString())
                    }
                    override fun onError(e: Throwable) { sbMsg("error: " + e.message) }
                    override fun onSubscribe(d: Disposable) { sbMsg("OnSubscribe.") }
                })
    }

    fun sbMsg(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}