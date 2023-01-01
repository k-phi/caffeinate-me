Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee lover
  I want to be able to order my coffee in advance

  Background:
    Given Cathy is a CaffeinateMe customer

  Rule: Orders placed close to the store should be considered as urgent
    Example:
      Given Cathy is 100 metres from the coffee shop
      When Cathy orders a "large cappuccino"
      Then Barry should receive the order
      And Barry should know that the order is Urgent

    Example:
      Given Cathy is 300 metres from the coffee shop
      When Cathy orders a "large cappuccino"
      Then Barry should receive the order
      And Barry should know that the order is Normal

  Rule: Buyers can specify their preferences when they order
    Example: Buyers can add a comment with their order
      When Cathy orders a "large cappuccino" with a comment "Double sugar"
      Then Barry should receive the order
      And the order should have the comment "Double sugar"
