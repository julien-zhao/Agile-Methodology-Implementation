Feature: Associate and update a student's address

  As an administrator,
  I want to associate a student with an address and update it when needed,
  So that I can manage the information in a centralized and consistent manner.

  Scenario Outline: Associate a student with an address
    Given a student named "<name>"
    And an address "<street>"
    When I associate the student "<name>" with the address "<street>"
    Then the student "<name>" should have the address "<street>"

    Examples:
      | name    | street               |
      | Alice   | 123 Main Street      |
      | Bob     | 456 Central Street   |
      | Claire  | 789 Paris Avenue     |

  Scenario Outline: Update a student's address
    Given a student named "<name>" with an existing address "<old_street>"
    And a new address "<new_street>"
    When I update the address of the student "<name>" to "<new_street>"
    Then the student "<name>" should have the new address "<new_street>"
    And the address "<old_street>" should no longer be associated with the student "<name>"

    Examples:
      | name    | old_street          | new_street            |
      | Alice   | 123 Main Street     | 101 Oak Road         |
      | Bob     | 456 Central Street  | 500 Elm Street       |
      | Claire  | 789 Paris Avenue    | 1000 Sunset Boulevard |


  Scenario Outline: Anomaly when a student is associated with a null or empty address
    Given a student named "<name>" without an address
    When I attempt to associate the student "<name>" with an address "<street>"
    Then an anomaly should be generated indicating that the address cannot be null or empty

    Examples:
      | name   | street             |
      | Alice  | 1 rue victor hugo  |







