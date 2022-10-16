package tech.alvarez.employeedirectory

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import tech.alvarez.employeedirectory.model.EmployeeList

object RetrofitHelper {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.EMPLOYEES_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}

interface EmployeesApi {
    @GET("/sq-mobile-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeList>
}