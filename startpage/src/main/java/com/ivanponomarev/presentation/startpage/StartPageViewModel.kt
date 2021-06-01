package com.ivanponomarev.presentation.startpage

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanponomarev.domain.GetAllReposUseCase
import com.ivanponomarev.domain.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartPageViewModel @Inject constructor(
    private var getAllReposUseCase: GetAllReposUseCase) : ViewModel() {

    var name = ObservableField<String>()

    var myResponseRepos: MutableLiveData<List<Repo?>> = MutableLiveData()
    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    init {
        Log.i("mainViewModel", "Created")
    }

    fun setName(name: String?) { this.name.set(name) }

    fun updateActionBarTitle(title: String) = _title.postValue(title)

    fun fetchReposData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repos = getAllReposUseCase.getAllRepos()
                Log.i("Repos", repos.toString())

                myResponseRepos.postValue(repos)
            } catch (e: Exception) {
                e.message?.let { Log.i("Repos", it) }
            }
        }
    }

}