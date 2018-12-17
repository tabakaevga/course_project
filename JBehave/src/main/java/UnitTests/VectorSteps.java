package UnitTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.function.Executable;

import Tests.JBehave.Vector;


public class VectorSteps {
	
	private Vector vector;
	
	private boolean operationCurrentResult;
	
	private String operationCurrent;
	
	ArrayList<Integer> resultVector;
	
	@Given("a vector with join, cross and isIn operations")
	public void operationInitialization()
	{
		vector = new Vector();
	}
	
	@When("[2, -5] [-5, 72]+ is called")
	public void joinOperationCommence()
	{
		vector.operation("[2, -5] [-5, 72]+");
		resultVector = vector.vector;
	}
	
	@Then("the result should be:2,-5,72")
	public void joinOperationResult()
	{
		ArrayList<Integer> expectedVector = new ArrayList<Integer>();
		expectedVector.add(2);
		expectedVector.add(-5);
		expectedVector.add(72);
		assertEquals(expectedVector, resultVector);
	}
	
	@When("[2, -5] [-5, 72]* is called")
	public void crossOperationCommence()
	{
		vector.operation("[2, -5] [-5, 72]*");
		resultVector = vector.vector;
	}
	
	@Then("the result should be:-5")
	public void crossOperationResult()
	{
		ArrayList<Integer> expectedVector = new ArrayList<Integer>();
		expectedVector.add(-5);
		assertEquals(expectedVector, resultVector);
	}
	
	@When("[2, -5] -5? is called")
	public void isInOperationCommence()
	{
		operationCurrentResult = vector.operation("[2, -5] -5?");
		resultVector = vector.vector;
	}
	
	@Then("the result should be:true")
	public void isInOperationResult()
	{
		ArrayList<Integer> expectedVector = new ArrayList<Integer>();
		expectedVector.add(-5);
		assertTrue(operationCurrentResult);
	}
	
	@When("wrong operation is called")
	public void incorrectOperationCommence()
	{
		operationCurrent = "wrong operation";
	}
	
	@Then("the result should be: IllegalArgumentException")
	public void incorrectOperationResult()
	{
		Executable invalidStackOperation = () -> {vector.operation(operationCurrent);};
		assertThrows(IllegalArgumentException.class, invalidStackOperation,"No elements in stack"); 		
	}

}
