package tech.alvarez.employeedirectory

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.alvarez.employeedirectory.ui.EmployeesViewModel
import tech.alvarez.employeedirectory.ui.EmployeesViewModelFactory

@RunWith(AndroidJUnit4::class)
class DirectoryTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
//        composeTestRule.launchJetNewsApp(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun app_launches() {
        composeTestRule.onNodeWithText("Top stories for you").assertExists()
    }
}