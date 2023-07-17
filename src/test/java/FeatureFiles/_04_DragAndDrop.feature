Feature: Drag Functionality
  Background:
    Given Open Application
    When Navigate To Drag Page

    @Smoke
    Scenario: Drag And Drop
      When User Complates Drag And Drop
      Then Succes Message Should Display

