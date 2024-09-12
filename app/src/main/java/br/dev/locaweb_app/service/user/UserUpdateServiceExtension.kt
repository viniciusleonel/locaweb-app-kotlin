package br.dev.locaweb_app.service.user

import android.util.Log
import br.dev.locaweb_app.model.ErrorResponse
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.model.user.UserUpdate
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun UserUpdate.update(
    id: Long,
    onSuccess: (UserLoginResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().updateUserById(id, this)
    call.enqueue(object : Callback<UserLoginResponse> {
        override fun onResponse(
            call: Call<UserLoginResponse>,
            response: Response<UserLoginResponse>
        ) {
            if (response.isSuccessful) {
                val userUpdateResponse = response.body()
                userUpdateResponse?.let { onSuccess(it) }
                Log.i("Update", "onResponse $userUpdateResponse")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: errorResponse?.email
                onFailure(errorMessage ?: "Erro desconhecido")
                Log.i("Update", "onResponse $errorBody")
            }
        }

        override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
            val errorMessage = t.message ?: "Unknown Error"
            onFailure(errorMessage)
            Log.i("Update", "onResponse ${t.message}")
        }

    })
}