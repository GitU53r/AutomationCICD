
@tag
Feature: Submit order from ecommerce website
  
  Background:
  Given: I landed on the Ecommerce page

  Scenario Outline: Positive Test for Submit Order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page
    
    Examples: 
      | username  				  | password 	| productName |
      |navani@gmail.com 		| Prajan@10	| ZARA COAT 3 |