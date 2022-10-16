package tech.alvarez.employeedirectory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tech.alvarez.employeedirectory.ui.detail.DetailScreen
import tech.alvarez.employeedirectory.ui.list.EmployeesScreen
import tech.alvarez.employeedirectory.ui.list.EmployeesViewModel
import tech.alvarez.employeedirectory.ui.list.EmployeesViewModelFactory
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as DirectoryApp).container

        setContent {

            val viewModel: EmployeesViewModel by viewModels {
                EmployeesViewModelFactory(appContainer.employeesRepository)
            }
//            val viewModel: EmployeesViewModel = viewModel(factory = EmployeesViewModel.provideFactory(appContainer.employeesRepository))

            viewModel.loadEmployees()

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    AlvarezTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
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
                                EmployeesScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(paddingValues)
                                ) {
                                    Log.e("daniel", ">>> $it")
                                    navController.navigate("detail/$it")
                                }
                            }
                        }
                    }

                }
                composable("detail/{uuid}", arguments = listOf(
                    navArgument("uuid") { type = NavType.StringType }
                )) {
                    val uuid = it.arguments?.getString("uuid")
                    requireNotNull(uuid)
                    DetailScreen(uuid, employeesViewModel = viewModel) {
                        navController.popBackStack()
                    }
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlvarezTheme {
//        EmployeeLazyColumn(employess)
    }
}