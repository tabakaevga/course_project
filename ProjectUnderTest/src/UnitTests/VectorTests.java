package UnitTests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import VectorAndStack.Vector;

class VectorTests {

	ArrayList<Integer> joinExpected = new ArrayList<Integer>() {{
	    add(2);
	    add(-5);
	    add(72);
	}};
	
	ArrayList<Integer> crossExpected = new ArrayList<Integer>() {{
	    add(-5);
	}};
	
	@Test
	void correctVectorJoinOperations() {
		Vector vector = new Vector();
		vector.operation("[2, -5] [-5, 72] +");
		assertArrayEquals(joinExpected.toArray(), vector.vector.toArray());

	}
	
	@Test
	void correctVectorCrossOperations() {
		Vector vector = new Vector();
		vector.operation("[2, -5, 72] [-5] *");	
		assertArrayEquals(crossExpected.toArray(), vector.vector.toArray());
	}
	
	@Test
	void correctVectorIsInOperations() {
		Vector vector = new Vector();
		assertTrue(vector.operation("[2, -5, 72] 72 ?"));		
	}

}
