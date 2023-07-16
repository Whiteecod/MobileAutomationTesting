Feature: Login Functionality

  @Smoke
  Scenario: Login with valid credentials
    Given Open Application
    When Navigate To Login Page
    And Enter Credentials And Click Login Button
    Then User Should Login Successfully