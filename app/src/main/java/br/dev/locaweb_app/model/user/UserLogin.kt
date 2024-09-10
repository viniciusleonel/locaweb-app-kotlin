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
    val password: String
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

fun UserLogin.login(userLogin: UserLogin, navController: NavController, context: Context)  {
    val call = RetrofitFactory().getUserService().login(userLogin)
    call.enqueue(object : Callback<UserLoginResponse>{
        override fun onResponse(
            call: Call<UserLoginResponse>,
            response: Response<UserLoginResponse>
        ) {
            if (response.isSuccessful) {
                val userLoginResponse = response.body()
                Log.i("LOGIN", "onResponse $userLoginResponse")
                loginSuccessToast(context)
                navController.navigate("profile")
            } else {
                val errorBody = response.errorBody()?.string()
                Log.e("LOGIN", "Error Response: $errorBody")
            }
        }

        override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
            t.message?.let { loginFailureToast(context, it) }
            Log.i("LOGIN", "onFailure ${t.message}")
        }


    })

    Log.i("LOGIN", "onResponse ${userLogin.username}")
    Log.i("LOGIN", "onResponse ${userLogin.password}")

    return
}
