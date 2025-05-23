@web
Feature: checkout

  Background:
    Given user is on home page
    And user click login bar
    And user input username with "logintes123"
    And user input password with "logintes123"
    When user click login button
    Then user will be on homepage with login user

  @checkout-start
  Scenario: User proceeds to checkout
    Given user click product
    When user is on add cart page
    And user click add to cart
    Then user will be get message "Product added."
    When user click cart bar
    Then user will be on cart pages
    And user click order button
    And user enters name "Ridwan"
    And user enters country "indonesia"
    And user enters city "Jakarta"
    And user enters creditCard "0812222"
    And user enters month "05"
    And user enters year "2025"
    When user clicks the purchase button
    Then user should see a confirmation message "Thank you for your purchase!" with information order