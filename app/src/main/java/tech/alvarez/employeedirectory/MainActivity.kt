package tech.alvarez.employeedirectory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
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
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: DirectoryViewModel by viewModels()

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {

                    AlvarezTheme {
                        // A surface container using the 'background' color from the theme
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

                                                viewModel.loadEmployees()
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Default.Info,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    )
                                },
                            ) {
                                EmployeesScreen(directoryViewModel = viewModel) {
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
                    DetailScreen(uuid, directoryViewModel = viewModel) {
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