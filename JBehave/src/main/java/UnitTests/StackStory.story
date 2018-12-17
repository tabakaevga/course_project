Stack story
				 
Scenario: Pull from empty stack
Given a stack with the size of 1
When Pulled from empty stack
Then Throws IllegalArgumentException

Scenario: Push to exceeded stack
When 2 vectors are pushed to stack
Then Throws IllegalArgumentException

Scenario: Pop from Correct Stack
Given a stack with some vector
When vector is popped
Then vector is good
