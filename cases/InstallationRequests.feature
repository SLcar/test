Feature: Installation Requests

  Scenario: the customer is request installation successfully
    Given  customer request for product "BMW engine /2020"
    And  the preferred date is "1/1/2024"
    And  the preferred hour is "10:00 am "
    And  the installing location is " Nablus - Rafedia street- Hijawi buldings - ground floor"
    Then the request sent to installation to conform

  Scenario: the customer is request installation without filled the date
    Given customer request for product "BMW engine /2020"
    And the preferred date is ""
    And the preferred hour is "10:00 am "
    And the installing location is " Nablus - Rafedia street- Hijawi buldings - ground floor"
    Then the request not send to installation

  Scenario: the customer is request installation without filled the hour
    Given customer request for product "BMW engine /2020"
    And the preferred date is "1/1/2024"
    And the preferred hour is ""
    And the installing location is " Nablus - Rafedia street- Hijawi buldings - ground floor"
    Then the request not send to installation

  Scenario: the customer is request installation without filled the product
    Given customer request for product ""
    And the preferred date is "1/1/2024"
    And the preferred hour is "10:00 am"
    And the installing location is " Nablus - Rafedia street- Hijawi buldings - ground floor"
    Then the request not send to installation

  Scenario: the customer is request installation without filled the location
    Given customer request for product "BMW engine /2020"
    And  the preferred date is "1/1/2024"
    And the preferred hour is "10:00 am "
    And the installing location is ""
    Then the request not send to installation

  Scenario: the customer is request installation without preferred hour
    Given customer request for product "BMW engine /2020"
    And the preferred date is "Any Day"
    And the preferred hour is "Any Time"
    And the installing location is " Nablus - Rafedia street- Hijawi buldings - ground floor"
    Then the request sent to installation to conform


#####################customer cancel installation req #######################

  Scenario: customer can cancel pending request
    Given customer view pending request and the id "22"
    When customer enter exist requestId "1401988804"
    Then customer can cancel request

  Scenario: customer can't cancel non exits requestId
    Given customer view pending request and the id "22"
    When  customer enter non exist requestId "-1"
    Then customer can't cancel request

     #####################installer cancel installation req ######################
  Scenario: installer can cancel pending request
    Given installer view pending request
    When installer enter exist request id "1401988804"
    Then installer can cancel request

  Scenario: installer can't cancel non exits requestId
    Given installer view pending request
    When  installer enter non exist requestId "-1"
    Then installer can't cancel request
  Scenario: admin can cancel pending request
    Given admin view pending request
    When admin enter exist requestId "1401988804"
    Then admin can cancel request

  Scenario: admin can't cancel non exits requestId
    Given admin view pending request
    When  admin enter non exist requestId "-1"
    Then admin can't cancel request
####################################################################################################

  Scenario: Installer schedules pending installation appointment
    When the installer choose req with "1401988808"
    And  the preferred date is "3/3/2023"
    And the preferred hour is "10 Am"
    Then the installer is available send confirmation request to customer and setting request status as "scheduled"

  Scenario: Installer schedules pending installation with any date
    When the installer choose req with "1401988804"
    And  the req time in this date is "Any Day" and the time is "Any Time"
    Then installer send confirmation to customer with date and time and setting request status as "scheduled"


  Scenario:Installer schedules pending installation appointment
    Given the installer choose req with not allowed "-1"
    Then the installer can't schedule the requests

  Scenario: Installer schedules an installation appointment
    When the installer choose request with "1401988808" and installer not available
    Then the installer send email to customer suggested new time and date

  Scenario: Customer reschedules the installation with installer suggestion
    When the customer check new date and time From email
    And the preferred date is "4/4/2023"
    And the preferred hour is "9 Am"
    Then customer send confirmation to installer

  Scenario: Installer completes the installation
    Given the installer is completed the installation "1401988808"
    Then  setting request status as completed






