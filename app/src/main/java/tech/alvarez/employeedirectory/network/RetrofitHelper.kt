package tech.alvarez.employeedirectory.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import tech.alvarez.employeedirectory.BuildConfig
import tech.alvarez.employeedirectory.model.EmployeeList

object RetrofitHelper {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.EMPLOYEES_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

interface EmployeesService {
    @GET("/sq-mobile-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeList>
}