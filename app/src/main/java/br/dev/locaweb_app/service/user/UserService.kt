package br.dev.locaweb_app.service.user

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.ErrorResponse
import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    // http://localhost:8080/api
    // http://localhost:8080/api/user/login

    @POST("user/login")
    fun login(@Body userLogin: UserLogin): Call<UserLoginResponse>

    @GET("user/{id}")
    fun getUserById(@Path("id") id: Long): Call<UserLoginResponse>
}

fun loginSuccessToast(context: Context) {
    Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_LONG).show()
}

fun loginFailureToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun UserLoginResponse.toJson(): String {
    return Gson().toJson(this)
}

fun fromJsonToUserLoginResponse(json: String): UserLoginResponse {
    return Gson().fromJson(json, UserLoginResponse::class.java)
}


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

    Log.i("LOGIN", "onResponse ${this.username}")
    Log.i("LOGIN", "onResponse ${this.password}")

    return
}