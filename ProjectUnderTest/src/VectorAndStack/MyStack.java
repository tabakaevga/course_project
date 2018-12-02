package VectorAndStack;
import java.util.Stack;

//Класс стека
public class MyStack {
	
	private Stack<Vector> elements = new Stack<Vector>();
	
	private int sizeOfStack;
	
	public void pushToStack(Vector vector)
	{
		if (elements.size() >= sizeOfStack) 
		{
			throw new IllegalArgumentException("Size of stack is exceeded");
		}
		elements.push(vector);
	}
	
	public Vector pullFromStack()
	{
		if (elements.isEmpty())
		{
			throw new IllegalArgumentException("No elements in stack");
		}
		return elements.pop();
	}
	
	public boolean findValue(Vector value)
	{
		return elements.contains(value);
	}
	
	public int getSize()
	{
		return sizeOfStack;
	}
	
	public void setSize(int size)
	{
		sizeOfStack = size;
	}
	
	
	public MyStack(int size)
	{
		setSize(size);
	}

}
