package br.dev.locaweb_app.service

import br.dev.locaweb_app.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <reified T> executeApiCall(
    call: Call<T>,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (String) -> Unit
) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let {
                    onSuccess(it)
                } ?: run {
                    onFailure("Response body is null")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: errorResponse?.email
                onFailure(errorMessage ?: "Unknown error occurred")
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val errorMessage = t.message ?: "Unknown Error"
            onFailure(errorMessage)
        }
    })
}

inline fun <reified T> handleApiCall(
    call: Call<T>,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (String) -> Unit
) {
    executeApiCall(
        call,
        onSuccess = { response -> onSuccess(response) },
        onFailure = { error -> onFailure(error) }
    )
}
