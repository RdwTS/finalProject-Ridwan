package ridwan;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = ("ridwan"),
        features = ("src/test/resources/login.feature"),
        plugin = {"pretty","html:reports/loginCucumber.html", "json:reports/loginCucumber.json"}
)

public class CucumberTest {


}
