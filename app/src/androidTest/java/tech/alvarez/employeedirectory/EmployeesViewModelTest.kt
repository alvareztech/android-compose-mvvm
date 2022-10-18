package tech.alvarez.employeedirectory

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import tech.alvarez.employeedirectory.ui.EmployeesViewModel

class EmployeesViewModelTest {
    private val viewModel: EmployeesViewModel = EmployeesViewModel(EmployeesRepository())

    @Test
    fun testViewModelDefault() {
        val isRefreshing = viewModel.isRefreshing.value
        val employees = viewModel.employees.value
        assertEquals(false, isRefreshing)
        assertEquals(null, employees)
    }

    @Test
    fun test2() {
        runTest {
            val isRefreshing = viewModel.isRefreshing.value
            val employees = viewModel.employees.value
            viewModel.loadEmployees()
            assertEquals(true, isRefreshing)
        }
    }
}