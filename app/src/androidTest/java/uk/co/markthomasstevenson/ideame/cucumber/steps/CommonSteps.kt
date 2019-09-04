package uk.co.markthomasstevenson.ideame.cucumber.steps

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import uk.co.markthomasstevenson.ideame.R

open class CommonSteps {

    @When("I tap the Fab button")
    fun tap_fab_button() {
        onView(withId(R.id.fab)).perform(ViewActions.click())
    }

    @When("I navigate up")
    fun i_navigate_up() {
        Espresso.pressBack()
    }

    @And("I should see {string}")
    @Then("I should see {string}")
    fun i_should_see(text: String) {
        onView(ViewMatchers.withText(text)).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }

    @Then("I am shown a dialog")
    fun i_am_shown_a_dialog() {
        onView(withId(android.R.id.button1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @When("I tap the negative option")
    fun i_tap_negative_option() {
        onView(withId(android.R.id.button2)).perform(ViewActions.click())
    }

    @And("The {string} field is {string}")
    fun the_field_is_content(name: String, content: String) {
        val id = when(name) {
            "title" -> R.id.tv_title
            "elevator" -> R.id.tv_elevatorPitch
            else -> 0
        }
        onView(withId(id)).check(ViewAssertions.matches(withText(content)))
    }
}