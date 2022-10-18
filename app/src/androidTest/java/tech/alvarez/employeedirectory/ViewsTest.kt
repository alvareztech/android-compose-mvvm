package tech.alvarez.employeedirectory

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.model.EmployeeType
import tech.alvarez.employeedirectory.ui.list.EmployeeItem
import tech.alvarez.employeedirectory.ui.list.EmptyMessage

@RunWith(AndroidJUnit4::class)
class ViewsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testEmployeeItem_populateData() {
        composeTestRule.setContent {
            EmployeeItem(employee = employeeTest) {}
        }
        composeTestRule.onNodeWithText("Daniel Alvarez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Square One").assertIsDisplayed()
    }

    @Test
    fun testEmptyMessage_refreshingTrue() {
        composeTestRule.setContent {
            EmptyMessage(modifier = Modifier, isRefreshing = true)
        }
        composeTestRule.onNodeWithTag("progress").assertIsDisplayed()
    }

    @Test
    fun testEmptyMessage_refreshingFalse() {
        composeTestRule.setContent {
            EmptyMessage(modifier = Modifier, isRefreshing = false)
        }
        composeTestRule.onNodeWithText("No Employees").assertIsDisplayed()
    }
}

val employeeTest = Employee(
    uuid = "",
    fullName = "Daniel Alvarez",
    phoneNumber = "",
    emailAddress = "daniel@alvarez.tech",
    biography = "",
    photoUrlSmall = "",
    photoUrlLarge = "",
    team = "Square One",
    employeeType = EmployeeType.FULL_TIME
)