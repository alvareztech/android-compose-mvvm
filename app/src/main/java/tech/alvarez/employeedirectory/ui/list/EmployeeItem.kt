package tech.alvarez.employeedirectory.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.model.EmployeeType
import tech.alvarez.employeedirectory.ui.theme.AlvarezTheme

@Composable
fun EmployeeItem(employee: Employee, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = employee.photoUrlSmall,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CutCornerShape(10.dp))
                .background(Color.LightGray)

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                employee.fullName,
                style = MaterialTheme.typography.body1,
            )
            Text(
                employee.team,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 400,
    heightDp = 200
)
@Composable
fun EmployeeItemPreview() {
    val employee = Employee(
        uuid = "192873",
        fullName = "Daniel Alvarez",
        phoneNumber = "+512839287",
        emailAddress = "",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "Square Team",
        employeeType = EmployeeType.CONTRACTOR
    )
    AlvarezTheme {
        Surface {
            EmployeeItem(employee = employee, onClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun EmployeeItemLargePreview() {
    val employee = Employee(
        uuid = "192873",
        fullName = "Daniel Alejandro Alvarez Aquino",
        phoneNumber = "+512839287",
        emailAddress = "",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "Square Team",
        employeeType = EmployeeType.PART_TIME
    )
    EmployeeItem(employee = employee, onClick = {})
}