package br.dev.locaweb_app.service.user

import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserRegister
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.model.user.UserUpdate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    // http://localhost:8080/api/user
    // http://localhost:8080/api/user/login

    @POST("user/login")
    fun login(@Body userLogin: UserLogin): Call<UserLoginResponse>

    @POST("user")
    fun register(@Body userRegister: UserRegister): Call<UserRegisterResponse>

    @PUT("user/{id}")
    fun updateUserById(@Path("id") id: Long, @Body userUpdate: UserUpdate): Call<UserLoginResponse>

    @DELETE("user/{id}")
    fun deleteUserById(@Path("id") id: Long): Call<MessageResponse>
}
