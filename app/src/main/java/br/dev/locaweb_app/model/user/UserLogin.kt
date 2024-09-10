package br.dev.locaweb_app.model.user

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import br.dev.locaweb_app.service.RetrofitFactory
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UserLogin(
    val username: String,
    val email: String
)

data class UserLoginResponse(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("username") val username: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("isLoggedIn") val isLoggedIn: Boolean = false
)

fun loginSuccessToast(context: Context) {
    Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_LONG).show()
}

fun loginFailureToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun UserLogin.login(navController: NavController, context: Context) : UserLoginResponse? {
    val call = RetrofitFactory().getUserService().login(this)
    var callResponse: Response<UserLoginResponse>? = null
    call.enqueue(object : Callback<UserLoginResponse>{
        override fun onResponse(
            call: Call<UserLoginResponse>,
            response: Response<UserLoginResponse>
        ) {
            loginSuccessToast(context)
            navController.navigate("profile")
            callResponse = response
            Log.i("LOGIN", "onResponse ${callResponse?.body()}")
        }

        override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
            t.message?.let { loginFailureToast(context, it) }
            Log.i("LOGIN", "onFailure ${t.message}")
        }

    })

    return callResponse?.body()
}
