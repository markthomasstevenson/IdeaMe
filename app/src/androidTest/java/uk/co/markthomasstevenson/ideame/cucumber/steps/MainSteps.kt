package uk.co.markthomasstevenson.ideame.cucumber.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.assertion.ViewAssertions.matches
import uk.co.markthomasstevenson.ideame.SplashActivity
import androidx.test.rule.ActivityTestRule
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import uk.co.markthomasstevenson.ideame.R

import androidx.test.espresso.matcher.ViewMatchers.*
import cucumber.api.java.en.And
import cucumber.api.java.en.Then
import io.realm.Realm
import uk.co.markthomasstevenson.ideame.data.ideaDao

class MainSteps {
    private var rule: ActivityTestRule<*> = ActivityTestRule(SplashActivity::class.java, false, false)

    @Before
    fun startTests() {
        val realm = Realm.getDefaultInstance()
        realm.deleteAll()
        realm.close()
        rule.launchActivity(null)
    }

    private val waitForActivityTimeout = 10000L

    @Given("I am on the {string} screen")
    fun i_am_on_the_screen(name: String) {
        val id = when (name) {
            "idea list" ->
                R.id.fragment_idea_list
            "create idea" ->
                R.id.fragment_view_idea
            else ->
                R.id.fragment_view_idea
        }
        val wait = 1000L
        var totalWait = 0L
        while( totalWait < waitForActivityTimeout ) {
            try {
                onView(withId(id)).check(matches(isDisplayed()))
                return
            }catch( e: NoMatchingViewException){
                totalWait += wait
                Thread.sleep(wait)
            }
        }

        throw Exception("The $name screen was not displayed")
    }

    @And("I have {int} ideas")
    fun i_have_ideas(number: Int) {
        val realm = Realm.getDefaultInstance()
        val actualCount = realm.ideaDao().getIdeaCount()
        if(number != actualCount) {
            realm.close()
            throw Exception("The number of ideas is not $number, it is $actualCount")
        }
        realm.close()
        return
    }

    @Then("I should see {string}")
    fun i_should_see(text: String) {
        onView(withText(text)).check(matches(isCompletelyDisplayed()))
    }
}
