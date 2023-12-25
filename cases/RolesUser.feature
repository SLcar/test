Feature: User Roles
  Scenario: The Admin can manages products
    Given The admin is logged into the system
    When  The admin enter "1"
    Then  The Admin able to add, edit, or delete products

  Scenario: The  Admin can manages  categories
    Given The admin is logged into the system
    When  The admin enter "2"
    Then  The Admin should be able to create, edit, or delete categories

  Scenario: The Admin can manages user accounts
    Given The admin is logged into the system
    When  The admin enter "3"
    Then  The Admin able to create, edit, or delete user accounts

  Scenario: The Admin enter number not found
    Given The admin is logged into the system
    When  The admin enter "6"
    Then  The Admin should be enter one ,two,or three

  Scenario: The Customer can Browse products
    Given The Customer is logged into the system
    When  The Customer enter "1"
    Then  The Customer able to  Browse products

  Scenario: The Customer can  make purchases
    Given The Customer is logged into the system
    When  The Customer enter "2"
    Then  The Customer should be able to  make purchases

  Scenario: The Customer can manages view orders
    Given The Customer is logged into the system
    When  The Customer enter "3"
    Then  The Customer able to view orders

  Scenario: The Customer enter number not found
    Given The Customer is logged into the system
    When  The Customer enter "6"
    Then  The Customer should be enter one ,two,or three
