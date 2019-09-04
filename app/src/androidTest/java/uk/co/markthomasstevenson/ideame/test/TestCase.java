package uk.co.markthomasstevenson.ideame.test;

import cucumber.api.CucumberOptions;
@CucumberOptions(glue = "uk.co.markthomasstevenson.ideame.cucumber.steps",
        features = "features",
        tags = "@SmokeTest")
@SuppressWarnings("unused")
public class TestCase {
}