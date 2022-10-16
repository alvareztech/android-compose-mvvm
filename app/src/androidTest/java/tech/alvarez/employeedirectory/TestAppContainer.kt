package tech.alvarez.employeedirectory

import android.content.Context
import tech.alvarez.employeedirectory.data.AppContainer
import tech.alvarez.employeedirectory.data.employees.EmployeesRepository

class TestAppContainer(private val context: Context) : AppContainer {

    override val employeesRepository: EmployeesRepository by lazy {
        EmployeesRepository()
    }
}