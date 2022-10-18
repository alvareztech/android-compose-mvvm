package tech.alvarez.employeedirectory.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import tech.alvarez.employeedirectory.ui.EmployeesViewModel
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme

@Composable
fun DetailScreen(
    uuid: String,
    employeesViewModel: EmployeesViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val employee = employeesViewModel.getEmployee(uuid)
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.height(240.dp), contentPadding = PaddingValues(0.dp)) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = employee?.photoUrlLarge,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        employee?.fullName ?: "",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier
                            .align(
                                Alignment.BottomStart
                            )
                            .padding(horizontal = 16.dp)
                    )
                    IconButton(
                        onClick = { onBackClick() },
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            employee?.biography?.let {
                ContactItem(it, Icons.Default.Info)
            }
            employee?.team?.let {
                ContactItem(it, Icons.Default.Info)
            }
            employee?.phoneNumber?.let {
                ContactItem(it, Icons.Default.Phone)
            }
            employee?.emailAddress?.let {
                ContactItem(it, Icons.Default.Email)
            }
            employee?.employeeType?.let {
                ContactItem(it.title, Icons.Default.Info)
            }
        }
    }
}

@Composable
fun ContactItem(value: String, icon: ImageVector) {
    Row(
        Modifier
            .clickable { }
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Text(
            value,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    AlvarezTheme {
        Surface {
            ContactItem("123456789", Icons.Default.Phone)
        }
    }
}
