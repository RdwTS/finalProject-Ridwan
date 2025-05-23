package ridwan;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = ("ridwan"),
        features = {
                "src/test/resources/checkout.feature",
                "src/test/resources/API.feature"
        },
        plugin = {"pretty","html:reports/ApiAndWebAutomation.html", "json:reports/ApiAndWebAutomation.json"}
)

public class CucumberTest {


}
