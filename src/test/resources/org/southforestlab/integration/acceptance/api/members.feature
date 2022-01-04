@api
Feature: Get list of members

  Scenario: Should return a list of members
    When I make a GET call on /api/members
    Then I should receive 200 response status code
    And should receive a non-empty body