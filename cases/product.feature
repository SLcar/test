Feature: Product Category Management

  ################################### Add ##################################################
  Scenario: admin can add product
    Given the productId is "-1"
    And the productName is "The hood"
    And the Description is "BMW E46 2009"
    And the price is "350₪"
    And the availability is "available"
    And The img path is "BMW_E46_2009.jpg"
    And the CategoryName is "Exterior"
    Then admin can add product

  Scenario: admin enter id that already exist in category
    Given the productId is "2"
    And the productName is "The hood"
    And the Description is "BMW E46 2009"
    And the price is "350₪"
    And the availability is "available"
    And The img path is "BMW_E46_2009.jpg"
    And the CategoryName is "Exterior"
    Then admin can't add product

    Scenario: admin enter category that not exist
    Given the productId is "0000"
    And the productName is "wheel"
    And the Description is "BMW E46 2009"
    And the price is "1000₪"
    And the availability is "available"
    And The img path is "BMW_E46_2009.jpg"
    And the CategoryName is "Suspension"
    Then the category not found

################################### Update ##################################################

  Scenario: admin can update product in category
    Given the productId is "2"
    And the CategoryName is "Exterior"
    Then admin can update product


  Scenario: admin enter id not exist to update product
    Given the productId is "6000"
    And the CategoryName is "Exterior"
    Then admin can't update


  Scenario:admin enter category not exist to update the product
    Given the productId is "2"
    And the CategoryName is "Suspension"
    Then the category not found


################################### delete ##################################################

  Scenario: admin can delete product from category
    Given the productId is "2"
    And the CategoryName is "Exterior"
    Then the product will deleted

  Scenario: admin enter id not exist to delete
    Given the productId is "333"
    And the CategoryName is "Exterior"
    Then the product not deleted

  Scenario: admin enter category not exist to delete the product
    Given the productId is "2"
    And the CategoryName is "Suspension"
    Then the category not found

################################### Show ##################################################

  Scenario: admin show all product in category
    Given the CategoryName is "Exterior"
    Then  all product category shown

  Scenario: admin enter not exist category to show
    Given the CategoryName is "Suspension"
    Then the category not shown