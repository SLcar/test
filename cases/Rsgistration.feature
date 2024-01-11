Feature: user sign up
  Scenario: Customer can sign up
    Given the name is "Soma"
    And   the email "somaaaaaaa12@gmail.com"
    And   the  password "1111"
    And   the confirmPassword is "1111"
    And   the id is "12345678"
    And   The Address is "Quds Street"
    And   The phone Number is "0594444"
    Then  customer can sign up

  Scenario: Customer Enter different password
    Given the  password "1111"
    And   the confirmPassword is "2222"
    Then  try again to sign up!

  Scenario: Customer Entered an email that already exists
    Given the email "s12029704@stu.najah.edu"
    Then  try enter email to sign up!

