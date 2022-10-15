package tech.alvarez.employeedirectory

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import tech.alvarez.employeedirectory.model.EmployeeList

object RetrofitHelper {
    val baseUrl = "https://s3.amazonaws.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}

interface EmployeesApi {
    @GET("/sq-mobile-interview/employees.json")
    suspend fun getEmployees() : Response<EmployeeList>
}