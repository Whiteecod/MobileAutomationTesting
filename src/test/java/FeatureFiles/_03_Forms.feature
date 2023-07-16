Feature: Forms Functionality

  Background:
    Given Open Application
    When Navigate To Forms Page

  @Smoke
  Scenario: Turn On Switch
    When User Turns On Switch
    Then The Switch Should Be Turned On

  @Smoke
  Scenario: Select An Option From Dropdown
    When User Open Dropdown Menu
    And Selects Second Option
    Then Second option Should Be Selected
