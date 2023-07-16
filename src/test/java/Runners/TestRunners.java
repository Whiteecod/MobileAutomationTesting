package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            tags = "@Smoke",
            features = "src/test/java/FeatureFiles",
            glue = {"StepDefinitions"}
    )

    public class TestRunners extends AbstractTestNGCucumberTests {
}
