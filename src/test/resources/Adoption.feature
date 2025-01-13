Feature: Adoption of animals by a student
  As a student,
  I want to adopt animals
  So that I can give them a loving home.

  Scenario Outline: A student adopts animals
    Given a student named for final "<studentName>"
    And the available animals are:
      | Name         | Type  | Adopted |
      | Toto         | Dog   | false   |
      | Titi         | Cat   | false   |
    When the student "<studentName>" adopts the animal "<animalName>"
    Then the list of animals adopted by "<studentName>" is:
      | Name         | Type  |
      | <animalName> | <animalType> |
    And the adoption status of "<animalName>" is "true"

    Examples:
      | studentName | animalName | animalType |
      | Bob         | Toto       | Dog        |
      | Bob         | Titi       | Cat        |
