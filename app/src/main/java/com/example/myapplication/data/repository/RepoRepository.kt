package com.example.myapplication.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.myapplication.data.repository.network.datasource.RepoDataSource
import com.example.myapplication.data.repository.network.model.Repo
import com.example.myapplication.data.repository.room.data.RepoEntity
import com.example.myapplication.data.repository.room.database.RepoDataBase
import com.example.myapplication.utils.networkboundresource.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepoRepository( private val repoSource: RepoDataSource, private val db:RepoDataBase) {
    private val repoDao = db.repoDao()
    private val TAG:String = this.javaClass.name

    suspend fun fetchRepo(): Flow<Result<Repo>?>{
        return flow{
        }
    }

    fun fetchRepos() = networkBoundResource(
        query = {
            repoDao.getAll()
        },
        fetch = {
            delay(2000)
            repoSource.fetchRepos()
        },
        saveFetchResult = { repositories ->

            
            db.withTransaction {
                repoDao.deleteAll()
                repoDao.inserAll(
                    mapFromResultToList(repositories)
                )
            }
        }
    )


    //helper method by mapping Repo to List of RepoEntity
    private fun mapFromResultToList(data:Result<Repo>):List<RepoEntity>{
        val test = data.getOrNull()
        Log.d(TAG, "mapFromResultToList: testing ${test?.get(0)}")
        val result = test?.map{
            
            RepoEntity(id = it.id, url = it.url, full_name = it.full_name)
        }
        return result!!
    }




}