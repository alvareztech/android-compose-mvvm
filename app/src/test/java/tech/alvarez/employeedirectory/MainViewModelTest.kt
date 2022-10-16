package tech.alvarez.employeedirectory

import org.junit.Test
import tech.alvarez.employeedirectory.model.Employee

class MainViewModelTest {
    private val viewModel: DirectoryViewModel = DirectoryViewModel()

    @Test
    fun testViewModel() {
        var list: List<Employee>? = viewModel.employees.value
        list?.let {
            assert(list.isEmpty())
        }
    }
}