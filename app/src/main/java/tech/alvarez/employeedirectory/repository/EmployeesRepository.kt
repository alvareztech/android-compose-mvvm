package tech.alvarez.employeedirectory.repository

import retrofit2.Response
import tech.alvarez.employeedirectory.EmployeesService
import tech.alvarez.employeedirectory.RetrofitHelper
import tech.alvarez.employeedirectory.model.EmployeeList

class EmployeesRepository(private val apiSource: RetrofitHelper) {

    suspend fun fetchEmployees(): Response<EmployeeList> {
        val network = RetrofitHelper.retrofit.create(EmployeesService::class.java)
        return network.getEmployees()
    }
}