package tech.alvarez.employeedirectory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.alvarez.employeedirectory.model.Employee

@Composable
fun EmployeesScreen(directoryViewModel: DirectoryViewModel = viewModel(), onItemClick: (String) -> Unit) {
    val employees by directoryViewModel.employees.observeAsState(emptyList())
    EmployeeLazyColumn(employees, onItemClick)
}

@Composable
fun EmployeeLazyColumn(employees: List<Employee>, onItemClick: (String) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Header")
        }
        items(employees) { employee ->
            EmployeeItem(employee) {
                onItemClick(employee.uuid)
            }
        }
    }
}

@Preview
@Composable
fun holaPreview() {
//    EmployeeLazyColumn()
}