Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee lover
  I want to be able to order my coffee in advance

  Scenario Outline: Buyer orders a coffee when they are close to the coffee shop
    Given Cathy is <distance> metres from the coffee shop
    When Cathy orders a large cappuccino
    Then Barry should receive the order
    And Barry should know that the order is <expected status>

    Examples:
      | distance | expected status |
      |       50 |          Urgent |
      |      100 |          Urgent |
      |      300 |          Normal |
