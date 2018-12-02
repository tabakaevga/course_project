import java.util.Stack;

//Класс стека
public class MyStack {
	
	private Stack<Integer> elements = new Stack<Integer>();
	
	private int sizeOfStack;
	
	public void pushToStack(Integer number)
	{
		if (elements.size() >= sizeOfStack) 
		{
			throw new IllegalArgumentException("Size of stack is exceeded");
		}
		elements.push(number);
	}
	
	public Integer pullFromStack()
	{
		if (elements.isEmpty())
		{
			throw new IllegalArgumentException("No elements in stack");
		}
		return elements.pop();
	}
	
	public boolean findValue(Integer value)
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
		sizeOfStack = size;
	}

}
