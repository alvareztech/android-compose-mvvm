package tech.alvarez.employeedirectory.data.employees

import retrofit2.Response

import tech.alvarez.employeedirectory.model.EmployeeList
import tech.alvarez.employeedirectory.network.EmployeesApi
import tech.alvarez.employeedirectory.network.RetrofitHelper

class EmployeesRepository {

    suspend fun fetchEmployees(): Response<EmployeeList> {
        val network = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
        return network.getEmployees()
    }
}