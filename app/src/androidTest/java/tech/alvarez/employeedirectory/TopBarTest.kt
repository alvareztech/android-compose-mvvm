package tech.alvarez.employeedirectory

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class TopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopBar() {
        composeTestRule.setContent {
            DetailScreen(uuid = "") {

            }
        }
    }
}