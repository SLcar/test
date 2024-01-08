#################################order notification ############################
Feature: Manage Notification
Scenario: Notification to confirm The Customer Order
Given Order status changed "shipped"
Then  Customer should have an order notification about the shipped order

Scenario: Order status changed Notification For Customer to shipped
Given Order status changed "delivered"
Then  Customer should have an order notification about the delivered order

Scenario: Admin Canceled order Notification
Given Admin cancel customer order "Canceled"
Then  Customer should have an order notification about the Canceled order


