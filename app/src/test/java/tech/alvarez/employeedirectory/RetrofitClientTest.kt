package tech.alvarez.employeedirectory

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
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

    @Test
    fun testEmptyEmployeesService() {
        runTest {
            val service = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
            val response = service.getEmployeesEmpty()

            val errorBody = response.errorBody()
            assert(errorBody == null)

            val responseWrapper = response.body()
            assert(responseWrapper != null)
            assert(response.code() == 200)
        }
    }

    @Test
    fun testMalformedEmployeesService() {
        runTest {
            val service = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
            try {
                service.getEmployeesMalformed()
                fail("JsonDataException expected")
            } catch (_: JsonDataException) {
            }
        }
    }
}