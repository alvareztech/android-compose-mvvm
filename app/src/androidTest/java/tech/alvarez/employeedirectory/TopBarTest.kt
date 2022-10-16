package tech.alvarez.employeedirectory

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.model.EmployeeType
import tech.alvarez.employeedirectory.ui.list.EmployeeItem

@RunWith(AndroidJUnit4::class)
class TopBarTest {

    val employee = Employee(
        uuid = "",
        fullName = "Daniel Alvarez",
        phoneNumber = "",
        emailAddress = "daniel@alvarez.tech",
        biography = "",
        photoUrlSmall = "",
        photoUrlLarge = "",
        team = "Square 1",
        employeeType = EmployeeType.FULL_TIME
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopBar() {
        composeTestRule.setContent {
//            EmployeeItem(employee = employee) {
//
//            }
        }
    }
}