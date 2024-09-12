package br.dev.locaweb_app.service.user

import android.util.Log
import br.dev.locaweb_app.model.ErrorResponse
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun deleteUserById(
    id: Long,
    onSuccess: (MessageResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().deleteUserById(id)
    call.enqueue(object : Callback<MessageResponse> {
        override fun onResponse(
            call: Call<MessageResponse>,
            response: Response<MessageResponse>
        ) {
            if (response.isSuccessful) {
                val responseMessage = response.body()
                responseMessage?.let { onSuccess(it) }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: "Erro desconhecido"
                onFailure(errorMessage)
                Log.i("Delete", "onResponse $errorBody")
            }
        }

        override fun onFailure(
            call: Call<MessageResponse>,
            t: Throwable
        ) {
            val errorMessage = t.message ?: "Unknown Error"
            onFailure(errorMessage)
            Log.i("Delete", "onResponse ${t.message}")
        }

    })
}