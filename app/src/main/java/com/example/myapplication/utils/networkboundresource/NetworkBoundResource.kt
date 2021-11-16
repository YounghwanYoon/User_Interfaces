package com.example.myapplication.utils.networkboundresource

import com.example.myapplication.utils.states.ResourceState
import kotlinx.coroutines.flow.*

//NetworkBoundResource from google
//reusable function that handle each job
inline fun <ResultType, RequestType> networkBoundResource(
    //responsible for getting data from database
    crossinline query: () -> Flow<ResultType>,

    //responsible for fetching new data from Rest API
    crossinline fetch: suspend () -> RequestType,

    //responsible for storing fetched data to local database
    // (Server data store into SQL local database)
    crossinline saveFetchResult: suspend (RequestType) -> Unit,

    //decide whether new fetch should be call or not
    //return boolean whether it needs to cache or not by
    crossinline shouldFetch:(ResultType) -> Boolean = { true }//default true - it means if don't pass return type, then fetch such as when we open an app start fetch}

) = flow {
    //first is same as collecting of flow but only one time.
    //it means it returns only one list of data
    //example only one list of repository
    val data = query().first()

    //this will decide to fetch or not
    val flow = if(shouldFetch(data)){
        emit(ResourceState.Loading(data)) //hey we are loading

        //retrofit request could fail in many reasons.
        // Ex. internet connection fail or error from the server, so fetch from try & catch block
        try{
            saveFetchResult(fetch()) //request rest api fetch and then save in local database
            //when update is happened, we want our ui to update immediately by calling data from query
            //then use map(transform) to store data into State which will be available to use in UI.
            query().map{
                ResourceState.Success(it)
            }
        }
        //catch will handle data after failed with fetch
        //this means ui will be updated with a local/room data.
        //Also, inform error with fetch to user by using State.
        catch(throwable:Throwable){
            query().map{
                ResourceState.Error(it, throwable)
            }

        }

    }
    //this else means handle the case when fetch is not necessary
    //In another word, data is not updated and use old one.
    else{ // this
        query().map{
            ResourceState.Success(it)
        }
    }

    emitAll(flow)

}