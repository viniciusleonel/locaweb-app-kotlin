package br.dev.locaweb_app.service

import br.dev.locaweb_app.service.email.EmailService
import br.dev.locaweb_app.service.preferences.UserPreferencesService
import br.dev.locaweb_app.service.user.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://10.0.2.2:8080/api/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserService(): UserService {
        return retrofitFactory.create(UserService::class.java)
    }

    fun getPreferencesService(): UserPreferencesService {
        return retrofitFactory.create(UserPreferencesService::class.java)
    }

    fun getEmailService(): EmailService {
        return retrofitFactory.create(EmailService::class.java)
    }

}