package UnitTests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import Tests.JBehave.MyStack;
import Tests.JBehave.Vector;

public class StackSteps {
	
	Vector vectorMock = Mockito.mock(Vector.class);
	MyStack stack;
	Executable invalidStackOperation;
	String exceptionMessage;
	
	Vector correctVectorActual;
	
	@Given("a stack with the size of 1")
	public void createAStack() {

		stack = new MyStack(1);
	}
	
	@When("Pulled from empty stack")
	public void pullFromEmptyStack()
	{
		invalidStackOperation = () -> {stack.pullFromStack();};
		exceptionMessage = "No elements in stack";
	}
	
	@When("2 vectors are pushed to stack")
	public void pushElementsToStack()
	{
		stack.pushToStack(vectorMock); 
		invalidStackOperation = () -> {stack.pushToStack(vectorMock);};
		exceptionMessage = "Size of stack is exceeded";
	}
	
	@Then("Throws IllegalArgumentException")
	public void stackThrowsException()
	{ 	
		assertEquals(1, stack.getSize());
		assertThrows(IllegalArgumentException.class, invalidStackOperation, exceptionMessage); 
	}
	
	@Given("a stack with some vector")
	public void correctPoppingStack()
	{
		stack = new MyStack(2);
		stack.pushToStack(vectorMock);
		
	}
	
	@When("vector is popped")
	public void poppingStack()
	{
		correctVectorActual = stack.pullFromStack();
	}
	
	@Then("vector is good")
	public void stackIsPopped()
	{
		assertFalse(stack.findValue(correctVectorActual));
		assertEquals(vectorMock, correctVectorActual);
	}

}
