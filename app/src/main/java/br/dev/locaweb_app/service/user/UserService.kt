package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import retrofit2.Call
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