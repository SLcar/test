Feature: Manage the order page

  ############################# customer creat order ######################
  #*********** creat order after listing category product
  Scenario: customer request order with all info
    Given customer view available products for exist categories example "Exterior"
    And The Customer Name is "Saja"
    And The Customer Id is "12345678"
    When customer enter exist productsId to order "1"
    And the quantities is allowed "1"
    Then  the products should ordered
    And the order status is "pending"

  Scenario: customer request order with not allowed quantity
    Given customer view available products for exist categories example "Exterior"
    When customer enter exist productsId to order "1"
    And  the quantities not allowed "0"
    Then the customer should enter allowed quantity

  Scenario: customer request order not exist product
    Given customer view available products for exist categories example "Exterior"
    When customer enter not exist product id to order "-1"
    Then the customer should add exist product
############################# customer cancel order ######################

  Scenario: customer can cancel pending order
    Given The Customer Name is "Saja"
    And The Customer Id is "12345678"
    And   customer view "pending" orders
    When customer enter exist order "4928437108"
    Then customer can cancel order

  Scenario: customer can't cancel non exits orderId
    Given customer view "pending" orders
    And The Customer Name is "Saja"
    And The Customer Id is "12345678"
    When  customer enter exist order "-1"
    Then customer can't cancel order

############################################################################################################
  Scenario: customer can edit pending order
    Given customer view "pending" orders
    And The Customer Name is "Saja"
    And The Customer Id is "12345678"
    When  customer enter exist order "4928437108"
    When  customer enter exist  productId "9"
    Then  customer can edit Quantity To The Product

  Scenario: customer can't edit non exits orderId
    Given customer view "pending" orders
    And The Customer Name is "Saja"
    And The Customer Id is "12345678"
    When  customer enter exist order "4928437108"
    When  customer enter exist order "-1"
    Then  customer can't edit  Quantity


  Scenario: customer can't edit non exist productId in order
    Given customer view "pending" orders
    And The Customer Name is "Saja"
    And The Customer Id is "12345678"
    When  customer enter exist order "4928437108"
    When  customer enter exist  productId "9"
    Then  customer can't edit The Quantity of productId -1
    #########################################################################
  Scenario: Admin can edit customer orders
    Given admin choose view customer order
    And   The Customer Id is "12345678"
    And   The Customer Name is "Saja"
    When  enter exist order "4928437108"
    And   the order status is "pending"
    Then  admin can edit order status,delivered date

