Feature: Testing getSalaries method


  Scenario: Calculate the total sum of salaries for all employees
    Given there are librarians
    * there are managers
    * there are administrators
    When the method to retrieve the total sum of salaries is called
    Then the total sum of salaries for all employees should be 8200.0
