package tech.alvarez.employeedirectory.data

import android.content.Context
import tech.alvarez.employeedirectory.data.employees.EmployeesRepository

interface AppContainer {
    val employeesRepository: EmployeesRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val employeesRepository: EmployeesRepository by lazy {
        EmployeesRepository()
    }
}