package tech.alvarez.employeedirectory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme

//TopAppBar(title = { Text(text = "Details") }, navigationIcon = {
//    IconButton(onClick = { onBackClick() }) {
//        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
//    }
//})

@Composable
fun DetailScreen(
    uuid: String,
    directoryViewModel: DirectoryViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val employee = directoryViewModel.getEmployee(uuid)
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            employee?.let {
                ContactItem(it.biography, Icons.Default.Info)
                ContactItem(it.team, Icons.Default.Info)
                ContactItem(it.phoneNumber, Icons.Default.Phone)
                ContactItem(it.emailAddress, Icons.Default.Email)
                ContactItem(it.employeeType, Icons.Default.Add)
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
            .padding(8.dp)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
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
            ContactItem("9182731987238", Icons.Default.Phone)
        }
    }
}
