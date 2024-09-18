package br.dev.locaweb_app.service.email

import br.dev.locaweb_app.model.email.Email
import br.dev.locaweb_app.model.email.EmailDetails
import br.dev.locaweb_app.model.email.EmailsList
import br.dev.locaweb_app.model.email.SentEmail
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmailService {

    // http://localhost:8080/api/email

    @POST("email")
    fun sendEmail(@Body email: Email): Call<SentEmail>

    @GET("email/sent/{id}?sort=sentAt,desc")
    fun listSentEmails(@Path("id") id: Long): Call<EmailsList>

    @GET("email/received/{id}?sort=sentAt,desc")
    fun listReceivedEmails(@Path("id") id: Long): Call<EmailsList>

    @GET("email/{emailId}/user/{userId}")
    fun getEmailByIdAndUserId(
        @Path("emailId") id: Long,
        @Path("userId") userId: Long
    ): Call<EmailDetails>
}