package tech.alvarez.employeedirectory

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
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
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testTopBar() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.app_name))
            .assertIsDisplayed()

        composeTestRule.onRoot().printToLog("TAG")
    }

    @Test
    fun testSomething() {
        composeTestRule.setContent {
            EmployeeItem(employee = employee) {

            }
        }
    }
}