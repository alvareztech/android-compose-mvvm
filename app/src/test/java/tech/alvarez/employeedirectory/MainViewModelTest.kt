package tech.alvarez.employeedirectory

import org.junit.Test
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.viewmodels.EmployeesViewModel

class MainViewModelTest {
    private val viewModel: EmployeesViewModel = EmployeesViewModel()

    @Test
    fun testViewModel() {
        var list: List<Employee>? = viewModel.employees.value
        list?.let {
            assert(list.isEmpty())
        }
    }
}