package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features"
        ,glue={"stepDefinition"},monochrome = true,dryRun = false,
        plugin = {"pretty","json:target/jsonReports/cucumber-report.json",
                "json:target/jsonReports/maven-cucumber-report"}
)
public class MyTestRunner {


}
