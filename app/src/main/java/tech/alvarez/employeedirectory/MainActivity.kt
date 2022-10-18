package tech.alvarez.employeedirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import tech.alvarez.employeedirectory.ui.EmployeesViewModel
import tech.alvarez.employeedirectory.ui.MainContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: EmployeesViewModel by viewModels()
            var isInitialized by rememberSaveable { mutableStateOf(false) }
            if (!isInitialized) {
                isInitialized = true
                viewModel.loadEmployees()
            }
            MainContainer(viewModel)
        }
    }
}