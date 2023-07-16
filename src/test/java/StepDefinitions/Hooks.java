package StepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverManager;

import java.sql.Driver;

import static utilities.DriverManager.getAppiumDriver;

public class Hooks {
    @Before
    public void before(){
        System.out.println("Scenario Started");
    }
    @After
    public void after() {
        System.out.println("Scenario Finished");
        DriverManager.closeApplication();
    }
}
