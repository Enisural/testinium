Feature: Gittigidiyor Shopping
  Member wants to login to the gittigidiyor

  Background:
    Given the home page of gittigidiyor is displayed

  Scenario: Login to gittigidiyor
    When the member hover and clicks login
    Then the member on the login page
    When the member enters username "enis_ural@hotmail.com" and password "1680221e-"
    And Clicks to login button
    Then member must be on home page and login text should change to "Hesabım"


