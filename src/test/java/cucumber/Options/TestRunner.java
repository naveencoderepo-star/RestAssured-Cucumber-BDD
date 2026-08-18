package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = {"stepDefinitions"}, plugin = {"pretty", "html:target/cucumber-reports.html"}, monochrome = true)
public class TestRunner {


}
