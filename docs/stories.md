# Kotlin Birthday Party User Stories

## Story #1: Invitation List

Type: **feature**

_As a party planner, I can see all the invites that I've sent out and their status so that I can easily track who will be coming to my party._

### Design
![Mission Queue Mockup]()

### Acceptance Criteria

```gherkin
Scenario: Display invitations

Given at least one invitation has been sent
When I open the application
Then I see a scrollable list of invitations ordered newest first
And each row shows who I invited and their RSVP status
```

```gherkin
Scenario: Empty list

Given no invitations have been sent out
When I open the application
Then I see the message "No invitations sent"
```

```gherkin
Scenario: RSVP status badge colors

Given an inviation has RSVP status "Pending"
When I view the invitation list
Then the status badge is blue

Given an inviation has RSVP status "Declined"
When I view the invitation list
Then the status badge is red

Given an inviation has RSVP status "Attending"
When I view the invitation list
Then the status badge is green
```

## Story #2: Send Invitation
Type: **feature**

_As a party planner, I can create a new party invitation_

### Design

### Acceptance Criteria

```gherkin
Scenario: Navigate to invitation form

Given I am on the main screen
When I tap the create invitation button
Then I navigate to the invitation form
```

```gherkin
Scenario: Send invitation

Given I am on the invitation form screen
When I enter valid data
And I tap the send invitation button
Then I get a confirmation toast
And I navigate to the main screen
```

```gherkin
Scenario: Enter invalid data

Given I am missing required fields
Or I have entered invalid data
When I tap the send invitation button
Then I get an error toast 
And the invalid fields show an error message
And my invitation is not sent
```


## Story #3: Guest Metrics

Type: **feature**

_As a party planner, I can see stats on how many guests were invited, confirmed, and denied so that I can adjust my headcount for food and party favors_

### Design

### Acceptance Criteria

```gherkin
Scenario: Stats displayed

Given at least one invitation has been sent
When I view the top of the invitation list
Then I can see how many guests were invited
And I can see how many guests have accepted their invitation
And I can see how many guests have declined their invitation
```