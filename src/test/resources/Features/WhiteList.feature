Feature: White List Execution API

  @Add
  Scenario:As an MOJ-approval, I want to integrate with execution system to add identity to white list
    Given Set Environment
      | EnvironmrntType |
      | Staging         |
    And Get Access Token
      | UserName                         | Password         |
      | aSc5b2P909WdQi2Q6ej6tbJPFnVbYRAK | EX0ulVjrVU5r9JAx |
    And verify that identity had details from Moc
      | CRNumber   |
      | 4030206632 |
    When Execute Whi teList API
      | IdentityId | RequiredAction | ExpirationDate              |
      | 4030206632 | Add            | 2026-01-12 12:43:17.2797078 |
    Then validate that response is 200 Ok
    And validate response body

   @Delete
  Scenario:As an MOJ-approval, I want to integrate with execution system to delete identity to white list
     Given Get Access Token
       | UserName                         | Password         |
       | aSc5b2P909WdQi2Q6ej6tbJPFnVbYRAK | EX0ulVjrVU5r9JAx |
    And Set Environment
      | EnvironmrntType |
      | Staging         |
    And verify that identity had details from Moc
      | CRNumber   |
      | 4030206632 |
    When Execute WhiteList API
      | IdentityId | RequiredAction | ExpirationDate              |
      | 4030206632 | Delete         | 2026-01-12 12:43:17.2797078 |
     Then validate that response is 200 Ok
     And validate response body