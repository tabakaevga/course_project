Vector Story
					 
Scenario:  Correct join, cross and isIn tests
Given a vector with join, cross and isIn operations
When [2, -5] [-5, 72]+ is called
Then the result should be:2,-5,72
When [2, -5] [-5, 72]* is called
Then the result should be:-5
When [2, -5] -5? is called
Then the result should be:true

Scenario: Incorrect operation test
When wrong operation is called
Then the result should be: IllegalArgumentException
