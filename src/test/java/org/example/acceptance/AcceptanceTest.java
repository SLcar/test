package org.example.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features ="cases", glue = {"org.example.acceptance"} ,plugin = {"html:target/cucumber/wikipedia.html"})
public class AcceptanceTest {

}
