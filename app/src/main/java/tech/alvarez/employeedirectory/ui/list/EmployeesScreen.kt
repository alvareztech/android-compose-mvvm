package tech.alvarez.employeedirectory.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import tech.alvarez.employeedirectory.model.Employee

@Composable
fun EmployeesScreen(
    viewModel: EmployeesViewModel,
    onItemClick: (String) -> Unit
) {
    val employees by viewModel.employees.observeAsState(emptyList())
    val isRefreshing by viewModel.isRefreshing.observeAsState(false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Directory") },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        if (employees.isEmpty()) {
            EmptyMessage(modifier = Modifier.padding(paddingValues), isRefreshing = isRefreshing)
        } else {
            EmployeeItems(viewModel, employees, isRefreshing, onItemClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmployeeItems(
    viewModel: EmployeesViewModel,
    employees: List<Employee>,
    isRefreshing: Boolean,
    onItemClick: (String) -> Unit
) {
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
fun EmptyMessage(modifier: Modifier, isRefreshing: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isRefreshing) {
            CircularProgressIndicator()
        } else {
            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = Color.Gray)
            Text(text = "No Employees", style = MaterialTheme.typography.caption)
        }
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

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun LoadingViewPreview() {
    EmptyMessage(modifier = Modifier, isRefreshing = true)
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun EmptyMessagePreview() {
    EmptyMessage(modifier = Modifier, isRefreshing = false)
}