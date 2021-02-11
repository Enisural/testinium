Feature: Gittigidiyor Shopping
  Member wants to add item to basket

  Background:
    Given the home page of gittigidiyor is displayed

  Scenario: Login to gittigidiyor
    When member write "bilgisayar" on the search bar
    And  member clicks the find button
    Then the member on the "bilgisayar" page
    When the member goes second page
    Then member must be on second page and the selects random product
    When member adds item to the basket
    Then basket count must increased one
    When member clicks the basket and goes to basket page
    Then member must see the value of item price in the products page and the baskets page must be equal
    When member increases the product count to two
    When member removes items in basket page
    Then member must see the empty basket page
