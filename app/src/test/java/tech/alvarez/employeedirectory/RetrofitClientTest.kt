package tech.alvarez.employeedirectory

import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Retrofit
import tech.alvarez.employeedirectory.network.EmployeesApi
import tech.alvarez.employeedirectory.network.RetrofitHelper

class RetrofitClientTest {

    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = RetrofitHelper.retrofit
        assert(instance.baseUrl().toUrl().toString() == BuildConfig.EMPLOYEES_API_BASE_URL)
    }

    @Test
    fun testEmployeesService() {
        runTest {
            val service = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
            val response = service.getEmployees()

            val errorBody = response.errorBody()
            assert(errorBody == null)

            val responseWrapper = response.body()
            assert(responseWrapper != null)
            assert(response.code() == 200)
        }
    }
}