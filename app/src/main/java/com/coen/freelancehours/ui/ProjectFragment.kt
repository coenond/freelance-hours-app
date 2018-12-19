package com.coen.freelancehours.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.coen.freelancehours.R
import com.coen.freelancehours.api.FreelanceHoursApi
import com.coen.freelancehours.api.FreelanceHoursApiService
import com.coen.freelancehours.api.response.ProjectAllResponse
import com.coen.freelancehours.model.Project
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : Fragment() {

    lateinit var projectAdapter: ProjectAdapter
    private lateinit var freelanceHoursApiService: FreelanceHoursApiService
    var projects = ArrayList<Project>()

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        fun newInstance(): ProjectFragment {
            val fragmentHome = ProjectFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projects.add(Project(1, "Test Project", "12,50", 2))

        // Init RecyclerView
        projectAdapter = ProjectAdapter(projects, this.context!!)

        rv_project_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv_project_list.adapter = projectAdapter
        projectAdapter.notifyDataSetChanged()

        // Start API Service
        freelanceHoursApiService = FreelanceHoursApi.start()
        getAllProjects()
    }

    private fun getAllProjects() {
        freelanceHoursApiService.getAllProject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<ProjectAllResponse> {
                    override fun onSuccess(response: ProjectAllResponse) {
                        response.projects?.let {
                            projects.addAll(it)
                            projectAdapter.notifyDataSetChanged()
                        }
                    }
                    override fun onError(e: Throwable) { sbMsg("error: " + e.message) }
                    override fun onSubscribe(d: Disposable) { sbMsg("OnSubscribe.") }
                })
    }

    fun sbMsg(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}