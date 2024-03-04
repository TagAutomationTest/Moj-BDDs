package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/Features", glue = {"Steps"}, monochrome = true,
        plugin = {"pretty", "json:target/cucumber-report/cucumber.json",
   "rerun:target/cucumber-reports/rerun.txt"},  tags=("not @ignore and @Ticket"))
public class BddRunner {
	
}
