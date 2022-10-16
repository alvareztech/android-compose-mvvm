package tech.alvarez.employeedirectory.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import tech.alvarez.employeedirectory.BuildConfig
import tech.alvarez.employeedirectory.model.EmployeeList

object RetrofitHelper {
    val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okhttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            })
            .build()
        Retrofit.Builder().baseUrl(BuildConfig.EMPLOYEES_API_BASE_URL)
            .client(okhttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}

interface EmployeesApi {
    @GET("/sq-mobile-interview/employees.json")
    suspend fun getEmployees(): Response<EmployeeList>

    @GET("/sq-mobile-interview/employees_malformed.json")
    suspend fun getEmployeesMalformed(): Response<EmployeeList>

    @GET("/sq-mobile-interview/employees_empty.json")
    suspend fun getEmployeesEmpty(): Response<EmployeeList>
}