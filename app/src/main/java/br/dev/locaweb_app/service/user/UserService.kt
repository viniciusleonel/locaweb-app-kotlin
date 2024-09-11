package br.dev.locaweb_app.service.user

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import br.dev.locaweb_app.model.ErrorResponse
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

//fun UserLoginResponse.toJson(): String {
//    return Gson().toJson(this)
//}
//
//fun fromJsonToUserLoginResponse(json: String): UserLoginResponse {
//    return Gson().fromJson(json, UserLoginResponse::class.java)
//}
