package br.dev.locaweb_app.service.user

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import br.dev.locaweb_app.model.ErrorResponse
import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun UserLogin.login(
    navController: NavController,
    context: Context,
    onSuccess: (UserLoginResponse) -> Unit,
    onFailure: (String) -> Unit
) {
    val call = RetrofitFactory().getUserService().login(this)
    call.enqueue(object : Callback<UserLoginResponse> {
        override fun onResponse(
            call: Call<UserLoginResponse>,
            response: Response<UserLoginResponse>
        ) {
            if (response.isSuccessful) {
                val userLoginResponse = response.body()
                Log.i("LOGIN", "onResponse $userLoginResponse")
                loginSuccessToast(context)
                userLoginResponse?.let { onSuccess(it) }
                navController.navigate("profile")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ErrorResponse::class.java)
                }
                val errorMessage = errorResponse?.error ?: "Campos obrigatórios"
                if (errorBody != null) {
                    loginFailureToast(context, errorBody)
                }
                Log.i("LOGIN", "Error Response: $errorBody")
                onFailure(errorMessage)
            }
        }

        override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
            val errorMessage = t.message ?: "Unknown Error"
            onFailure(errorMessage)
            Log.i("LOGIN", "onFailure $errorMessage")
        }
    })
}