package tech.alvarez.employeedirectory.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.viewmodels.DirectoryViewModel

@Composable
fun EmployeesScreen(
    modifier: Modifier = Modifier,
    directoryViewModel: DirectoryViewModel = viewModel(),
    onItemClick: (String) -> Unit
) {
    val employees by directoryViewModel.employees.observeAsState(emptyList())
    if (employees.isEmpty()) {
        EmptyMessage()
    } else {
        EmployeeLazyColumn(employees, onItemClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeLazyColumn(employees: List<Employee>, onItemClick: (String) -> Unit) {
    val viewModel: DirectoryViewModel = viewModel()
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() },
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val grouped = employees.groupBy { it.employeeType }
            grouped.forEach { (header, items) ->
                stickyHeader {
                    HeaderList(header.title)
                }
                items(items) { employee ->
                    EmployeeItem(employee) {
                        onItemClick(employee.uuid)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyMessage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color.Gray)
        Text(text = "No Employees", style = MaterialTheme.typography.caption)
    }
}

@Composable
fun HeaderList(title: String) {
    Text(
        title, modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    )
}

@Composable
fun LoadingView() {
    CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun EmptyMessagePreview() {
    EmptyMessage()
}