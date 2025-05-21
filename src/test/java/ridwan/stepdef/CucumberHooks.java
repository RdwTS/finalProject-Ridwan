package ridwan.stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ridwan.BaseTest;

public class CucumberHooks extends BaseTest {

    @Before
    public void beforeTest() {
        System.out.println("Before");
        getDriver();
    }

    @After
    public void afterTest() {
        System.out.println("After");
        driver.close();
    }
}
