@login
Feature: Login

  @valid-login
  Scenario: Login using valid email
    Given user is on home page
    And user click login bar
    And user input username with "logintes123"
    And user input password with "logintes123"
    When user click login button
    Then user will be on homepage with login user

  @invalid-login
  Scenario: Login using invalid email and password
    Given user is on home page
    And user click login bar
    And user input username with "logintes121"
    And user input password with "logintes123"
    When user click login button
    Then user able to see error message "User does not exist."
    And user will be on homepage with no login user