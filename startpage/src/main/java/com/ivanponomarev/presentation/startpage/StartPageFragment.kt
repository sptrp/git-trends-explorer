package com.ivanponomarev.presentation.startpage

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
import com.ivanponomarev.gittrends.startpage.R
import com.ivanponomarev.gittrends.startpage.databinding.StartPageFragmentBinding
import com.ivanponomarev.presentation.details.RepoCardDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartPageFragment : Fragment() {

    private var myContext: FragmentActivity? = null

    private val startPageViewModel: StartPageViewModel by viewModels()

    private lateinit var activityMainContentBinding: StartPageFragmentBinding
    private val recyclerViewAdapter by lazy { RecyclerViewAdapter(this::showDetailsFragment) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activityMainContentBinding = StartPageFragmentBinding.inflate(layoutInflater)
        activityMainContentBinding.lifecycleOwner = this

        val view = activityMainContentBinding.root

        val recyclerView = activityMainContentBinding.recyclerView

        recyclerView.adapter = recyclerViewAdapter

        startPageViewModel.fetchReposData()
        startPageViewModel.myResponseRepos.observe(viewLifecycleOwner, Observer { response ->
            if (response.isEmpty().not()) {
                recyclerViewAdapter.setData(response)

            } else {
                //Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT)

            }
        })

        return view
    }

    private fun showDetailsFragment(data: Repo?) {

        val transaction = myContext!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.startPageFragment, RepoCardDetailsFragment(data))
        transaction.commit()

        transaction.addToBackStack("details")
    }

    override fun onAttach(context: Context) {

        myContext = activity as FragmentActivity

        super.onAttach(context)
    }

}