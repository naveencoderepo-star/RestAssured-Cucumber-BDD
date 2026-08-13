package cucumber.Options;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features", glue= {"stepDefinitions"}, plugin= {"pretty", "html:target/cucumber-reports.html"}, monochrome = true)
public class TestRunner {

//Builing Utility files video completed and need to wait for Rahulshetty server issue to resolve.
}
