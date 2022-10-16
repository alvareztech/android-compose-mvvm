package tech.alvarez.employeedirectory.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tech.alvarez.employeedirectory.ui.detail.DetailScreen
import tech.alvarez.employeedirectory.ui.list.EmployeesScreen
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme

@Composable
fun MainContainer(viewModel: EmployeesViewModel) {
    val navController = rememberNavController()
    AlvarezTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    EmployeesScreen(viewModel = viewModel) {
                        navController.navigate("detail/$it")
                    }
                }
                composable("detail/{uuid}", arguments = listOf(
                    navArgument("uuid") { type = NavType.StringType }
                )) { it ->
                    val uuid = it.arguments?.getString("uuid")
                    uuid?.let {
                        DetailScreen(it, employeesViewModel = viewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}