package com.ivanponomarev.presentation.repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.gittrends.startpage.databinding.ActivityMainContentFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private var myContext: FragmentActivity? = null

    private val startPageViewModel: StartPageViewModel by viewModels()

    private lateinit var activityMainContentBinding: ActivityMainContentFragmentBinding
    private val myAdapter by lazy { RecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activityMainContentBinding = ActivityMainContentFragmentBinding.inflate(layoutInflater)
        activityMainContentBinding.lifecycleOwner = this

        val view = activityMainContentBinding.root

        val recyclerView = activityMainContentBinding.recyclerView

        recyclerView.adapter = myAdapter

        startPageViewModel.fetchReposData()
        startPageViewModel.myResponseRepos.observe(viewLifecycleOwner, Observer { response ->
            if (response.isEmpty().not()) {
                myAdapter.setData(response)

            } else {
                //Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT)

            }
        })

        return view
    }

    private fun showDetailsFragment(data: Repo?) {

    }

    override fun onAttach(context: Context) {

        myContext = activity as FragmentActivity

        super.onAttach(context)
    }

}