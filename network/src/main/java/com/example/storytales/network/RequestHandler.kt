package com.example.storytales.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

sealed class NetworkResult<out T>{
    data class Success<T>(val result:T):NetworkResult<T>()
    data class Error<Nothing>(val body:String?,val exception: Exception):NetworkResult<Nothing>()
}

class RequestHandler(val httpClient:HttpClient) {

    suspend inline fun <reified B,reified R> executeRequest(
        method:HttpMethod,
        urlPathSegments:List<Any>,
        body:B? = null,
        queryParams:Map<String,Any>? = null
    ):NetworkResult<R>{
        return withContext(Dispatchers.IO){
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegment = urlPathSegments.map { it.toString() }
                        appendPathSegments(pathSegment)
                    }
                    body?.let {
                        setBody(body)
                    }
                    queryParams?.let {
                        it.forEach {(key,value)->
                            parameter(key, value)
                        }
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            }catch (e:Exception){
                val networkException = if (e is ResponseException){
                    val errorBody = e.response.body<DefaultError>()
                    when(e.response.status){
                        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(errorBody.message,e)
                        else -> NetworkException.NotFoundException("API Not Found",e)
                    }
                } else{
                    NetworkException.UnknownException(e.message?:"Unknown Error",e)
                }
                NetworkResult.Error(null,networkException)
            }
        }
    }

    suspend inline fun <reified R>get(
        urlPathSegments: List<Any>,
        queryParams:Map<String,Any>? = null
    ):NetworkResult<R> = executeRequest<Any,R>(
        method = HttpMethod.Get,
        urlPathSegments = urlPathSegments,
        queryParams = queryParams
    )

    suspend inline fun <reified B,reified R>post(
        urlPathSegments: List<Any>,
        body:B? = null
    ):NetworkResult<R> = executeRequest<B,R>(
        method = HttpMethod.Post,
        urlPathSegments = urlPathSegments.toList(),
        body = body
    )

}