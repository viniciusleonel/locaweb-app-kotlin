package br.dev.locaweb_app.service

import androidx.navigation.NavController
import br.dev.locaweb_app.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <reified T> executeApiCall(
    call: Call<T>,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (String) -> Unit,
    navController: NavController
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
                val statusCode = response.code()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: errorResponse?.email
                onFailure(errorMessage ?: "Unknown error occurred")
                if (statusCode == 401 || errorMessage.equals("Login expired!")) {
                    navController.navigate("login")
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val errorMessage = t.message ?: "Error connecting to server!"
            onFailure(errorMessage)
        }
    })
}

inline fun <reified T> handleApiCall(
    call: Call<T>,
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (String) -> Unit,
    navController: NavController
) {
    executeApiCall(
        call,
        onSuccess = { response -> onSuccess(response) },
        onFailure = { error -> onFailure(error) },
        navController = navController
    )
}
