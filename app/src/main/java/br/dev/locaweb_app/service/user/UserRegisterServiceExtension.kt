package br.dev.locaweb_app.service.user

import android.util.Log
import androidx.navigation.NavController
import br.dev.locaweb_app.model.ErrorResponse
import br.dev.locaweb_app.model.user.UserRegister
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun UserRegister.register(
    navController: NavController,
    onSuccess: (UserRegisterResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().register(this)
    call.enqueue(object : Callback<UserRegisterResponse> {
        override fun onResponse(
            call: Call<UserRegisterResponse>,
            response: Response<UserRegisterResponse>
        ) {
            if (response.isSuccessful) {
                val userRegisterResponse = response.body()
                userRegisterResponse?.let { onSuccess(it) }
                navController.navigate("login")
                Log.i("Register", "onResponse $userRegisterResponse")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: errorResponse?.email
                onFailure(errorMessage ?: "Erro desconhecido")
                Log.i("Register", "onResponse $errorBody")
            }
        }

        override fun onFailure(
            call: Call<UserRegisterResponse>,
            t: Throwable
        ) {
            val errorMessage = t.message ?: "Unknown Error"
            onFailure(errorMessage)
            Log.i("Register", "onResponse ${t.message}")
        }
    })
}