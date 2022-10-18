package tech.alvarez.employeedirectory

import junit.framework.TestCase.assertEquals
import org.junit.Test
import tech.alvarez.employeedirectory.ui.EmployeesViewModel

class EmployeesViewModelTest {

    private val viewModel: EmployeesViewModel = EmployeesViewModel()

    @Test
    fun testViewModel_defaultValues() {
        val isRefreshing = viewModel.isRefreshing.value
        val employees = viewModel.employees.value
        assertEquals(false, isRefreshing)
        assertEquals(null, employees)
    }
}