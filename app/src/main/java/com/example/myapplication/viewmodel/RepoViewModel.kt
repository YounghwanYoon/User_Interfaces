package com.example.myapplication.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.repository.RepoRepository
import com.example.myapplication.data.repository.network.datasource.RepoDataSource
import com.example.myapplication.data.repository.room.data.RepoEntity
import com.example.myapplication.data.repository.room.database.RepoDataBase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RepoViewModel(application: Application): AndroidViewModel(application) {
    private val TAG:String = this.javaClass.name


    private val repository:RepoRepository

    init{
        val repoSource = RepoDataSource()
        val db = RepoDataBase.getDatabase(application.applicationContext)
        repository = RepoRepository(repoSource, db)
    }
    //Flow is more flexible but livedata is more easy to apply with UI.
    // asLiveData() transform flow to liveData
    val repoData = repository.fetchRepos().asLiveData()

    fun testingData(){
      //  Log.d(TAG, "testingData: from repoviewmodel - ${repoData?.value?.data?.get(0)}")
    }




    /*
    private val repository:RepoRepository

    private val _RepoLiveData = MutableLiveData<List<RepoEntity>>()
    val repoLiveData: LiveData<List<RepoEntity>> = _RepoLiveData

    init{
        val repoSource = RepoDataSource()
        val db = RepoDataBase.getDatabase(application.applicationContext)
        repository = RepoRepository(repoSource, db)

        viewModelScope.launch{
            val repoData = db.repoDao().getAll()
            delay(2000)
            _RepoLiveData.value = repoData!!

        }
    }

     */





}