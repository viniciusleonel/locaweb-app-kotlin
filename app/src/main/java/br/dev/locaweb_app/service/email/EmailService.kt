package br.dev.locaweb_app.service.email

import br.dev.locaweb_app.model.email.Email
import br.dev.locaweb_app.model.email.SentEmail
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailService {

    // http://localhost:8080/api/email

    @POST("email")
    fun sendEmail(@Body email: Email) : Call<SentEmail>
}