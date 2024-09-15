package br.dev.locaweb_app.service.preferences

import br.dev.locaweb_app.model.preferences.UserPreferences
import br.dev.locaweb_app.model.preferences.UserPreferencesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserPreferencesService {

    // http://localhost:8080/api
    // http://localhost:8080/api/preferences

    @PUT("preferences/{id}")
    fun updatePreferences(@Path ("id") id: Long, @Body userPreferences: UserPreferences) : Call<UserPreferencesResponse>

    @GET("preferences/user/{2id}")
    fun loadUserPreferences(@Path ("id") id: Long) : Call<UserPreferencesResponse>

}