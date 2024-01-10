Feature: Product Category Search

  Scenario Outline: Search for a product but user enter category not found
    Given the CategoryName  <catName>
    Then The category not found
    Examples:
      | catName      |
      | "Suspension" |

  Scenario Outline: Search for a product by ID and found it
    Given the CategoryName  <catName>
    When the user searches for a product with ID <id>
    Then the system should display the product with ID <string>
    Examples:
      | catName    | id  | string |
      | "Exterior" | "1" | "1"    |
      | "Exterior" | "2" | "2"    |

   Scenario: Search for a product by ID but not found
    Given the CategoryName  "Exterior"
    When  the user searches for a product with ID "0000"
    Then  the system should display the product with ID "0000" not found


  Scenario: Search for a product by name and found it
    Given the CategoryName  "Exterior"
    When the user searches for a product with name "The hood"
    Then the system should display products with the name "The hood"

  Scenario: Search for a product by name but name not found
    Given the CategoryName  "Exterior"
    When the user searches for a product with name "--"
    Then the system should display products with the name "--" not found

  Scenario: Search for a product by description or part of it and found it
    Given the CategoryName  "Exterior"
    When the user searches for a product with description "BMW"
    Then the system should display products with the description "BMW"

  Scenario: Search for a product description and not found
    Given the CategoryName  "Exterior"
    When the user searches for a product with description "--"
    Then the system should display products with the description "--" not found

  Scenario: Search for a product by  availability
    Given the CategoryName  "Exterior"
    When the user searches for a product with availability "available"
    Then the system should display products with the availability "available"

  Scenario: Search for a product by  availability but not found
    Given the CategoryName  "Exterior"
    When the user searches for a product with availability "--"
    Then the system should display products with the availability "--" not found


