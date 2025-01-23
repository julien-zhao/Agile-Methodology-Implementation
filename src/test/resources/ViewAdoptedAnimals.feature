Feature: Viewing the list of adopted animals
  As a student,
  I want to see the list of animals I have adopted
  So that I can confirm they are properly registered.

  Scenario Outline: A student views the list of their adopted animals
    Given a student named for final view "<studentName>"
    And the animals adopted by "<studentName>" are:
      | Name         | Type  |
      | <animal1>    | <type1> |
      | <animal2>    | <type2> |
    When "<studentName>" views their list of adopted animals
    Then the displayed list is:
      | Name         | Type  |
      | <animal1>    | <type1> |
      | <animal2>    | <type2> |

    Examples:
      | studentName | animal1 | type1 | animal2 | type2 |
      | Alice       | Titi    | Cat   | Bella   | Cat   |
      | Bob         | Toto    | Cat   | Bella   | Cat   |