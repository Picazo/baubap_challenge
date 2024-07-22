package com.juliopicazo.baubapchallenge

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.juliopicazo.baubapchallenge.ui.screen.login.LoginScreen
import com.juliopicazo.baubapchallenge.ui.theme.BaubapChallengeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun loginScreen_initialState() {
        composeTestRule.activity.setContent {
            BaubapChallengeTheme {
                LoginScreen()
            }
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.login_button))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.email_placeholder))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.password_placeholder))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.login_button))
            .assertIsNotEnabled()
    }

    @Test
    fun loginScreen_enableLoginButton() {
        composeTestRule.activity.setContent {
            BaubapChallengeTheme {
                LoginScreen()
            }
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.email_placeholder))
            .performTextInput("test@test.com")
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.password_placeholder))
            .performTextInput("pass123")

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.login_button))
            .assertHasClickAction()
    }

    @Test
    fun loginScreen_closeApp() {
        composeTestRule.activity.setContent {
            BaubapChallengeTheme {
                LoginScreen()
            }
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.getString(R.string.close_app_description))
            .performClick()

        assert(composeTestRule.activity.isFinishing)
    }
}
