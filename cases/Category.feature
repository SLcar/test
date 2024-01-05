Feature: Category Management

  Scenario: Admin can add new Category
    Given The Name of Category is "New"
    Then  The Admin can add the Category

  Scenario: Admin enter category that already exist
    Given The Name of Category is "Exterior"
    Then  Admin can't add Category

  Scenario: Admin can edit the name of category
    Given The Name of Category is "New"
    Then  The Admin can edit name of Category

  Scenario: Admin can't edit the name of category because not found to edit
    Given The Name of Category is "rrr"
    Then  The Admin can't edit name of Category

  Scenario: Admin can delete category
    Given The Name of Category is "New"
    Then  The Admin can delete this Category

  Scenario: Admin enter category not exist to delete
    Given The Name of Category is "rrr"
    Then  the Admin can't delete Category

