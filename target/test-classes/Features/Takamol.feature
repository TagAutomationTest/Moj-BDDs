Feature: Move da3em Ticket

  @Ticket
  Scenario:As a Da3em Api User, I want to update da3em ticket
    Given Get Access Token
      | UserName                         | Password         |TakamolBaseURI                     |
      | aSc5b2P909WdQi2Q6ej6tbJPFnVbYRAK | EX0ulVjrVU5r9JAx |https://api-test.moj.gov.local/    |
    And  Get ticket information
      | TicketNo |
      | INC36637 |
    Then Update ticket
      | TicketNo | TicketStatusToBe |
      | INC36637 | 2                |

