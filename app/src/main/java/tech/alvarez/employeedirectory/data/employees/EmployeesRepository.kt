package tech.alvarez.employeedirectory.data.employees

import retrofit2.Response
import tech.alvarez.employeedirectory.EmployeesService
import tech.alvarez.employeedirectory.RetrofitHelper
import tech.alvarez.employeedirectory.model.EmployeeList

class EmployeesRepository {

    suspend fun fetchEmployees(): Response<EmployeeList> {
        val network = RetrofitHelper.retrofit.create(EmployeesService::class.java)
        return network.getEmployees()
    }
}