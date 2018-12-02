import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vector 
{
	MyStack vector = new MyStack(0);
	
	public Vector()
	{
	}
	
	private String arrayRegex = "^\\[([-]?\\d+(,\\s*[-]?\\d+)*)?\\]\\s*";
	
	
	//–егул€рные выражени€ дл€ операций над вектором
	private String[] operationsRegex = 
		{
			arrayRegex + arrayRegex.replace("^", "") + "[+]$",	//ќбъединение векторов
			arrayRegex + "[-]?\\d+\\s*[?]$", 		//¬хождение числа в вектор
			arrayRegex + arrayRegex.replace("^", "") + "[*]$",	//ѕересечение векторов
		};
	
	public boolean operation(String inputString) 
	{
		int handleOption = -1;
		for (int i = 0; i < operationsRegex.length; i++)
		{
			Pattern pattern = Pattern.compile(operationsRegex[i]);
			Matcher matcher = pattern.matcher(inputString);
			if (matcher.matches())
			{
				handleOption = i;
				break;
			}
		}
		if (handleOption == -1)
		{
			throw new IllegalArgumentException("Something is wrong with operations method");
		}
		return operationsHandler(handleOption, inputString);
	}
	
	private boolean operationsHandler(int operation, String inputString)
	{
		switch (operation)
		{
		case 0:
			return joinVectors(inputString);
		case 1:
			return findIfIsInVector(inputString);
		case 2:
			return crossVectors(inputString);
		default:
				throw new IllegalArgumentException("Something is wrong with operations");
		}	
	}
	
	private boolean findIfIsInVector(String inputString) 
	{
		Pattern pattern = Pattern.compile(arrayRegex);
		Matcher matcher = pattern.matcher(inputString);
		matcher.find();
		MyStack vectorLeft = parseStringToMyStack(matcher.group(0));
		Integer number = Integer
				.parseInt(inputString.replaceAll(arrayRegex, "").replaceAll("\\s*[?]", ""));
		return vectorLeft.findValue(number);
	}

	private boolean joinVectors(String inputString)
	{
		Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
		Matcher matcher = pattern.matcher(inputString);
		matcher.find();
		int endOfFirst = matcher.end();
		MyStack vectorLeft = parseStringToMyStack(matcher.group(0));
		matcher.find(endOfFirst);
		MyStack vectorRight = parseStringToMyStack(matcher.group(0));
		MyStack newVector = vectorLeft;
		for (int i = 0; i < vectorRight.getSize(); i++)
		{
			int pulledValue = vectorRight.pullFromStack();
			if (!newVector.findValue(pulledValue))
			{
				newVector.setSize(newVector.getSize() + 1);
				newVector.pushToStack(pulledValue);
			}
		}
		vector = newVector;
		return false;
	}
	
	private boolean crossVectors(String inputString)
	{
		Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
		Matcher matcher = pattern.matcher(inputString);
		matcher.find();
		int endOfFirst = matcher.end();
		MyStack vectorLeft = parseStringToMyStack(matcher.group(0));
		matcher.find(endOfFirst);
		int minSize = vectorLeft.getSize();
		MyStack vectorRight = parseStringToMyStack(matcher.group(0));
		if (minSize > vectorRight.getSize())
		{
			minSize = vectorRight.getSize();
		}
		MyStack newVector = new MyStack(minSize);
		for (int i = 0; i < vectorRight.getSize(); i++)
		{
			int pulledValue = vectorRight.pullFromStack();
			if (vectorLeft.findValue(pulledValue))
			{
				newVector.pushToStack(pulledValue);
			}
		}
		vector = newVector;
		return false;
	}
	
	private MyStack parseStringToMyStack(String stringToParse)
	{
		String[] items = stringToParse.replaceAll("\\[", "").replaceAll("\\]", "")
				.replaceAll("\\s", "").split(",");
		MyStack stack = new MyStack(items.length);
		for (int i = 0; i < items.length; i++) {
		    stack.pushToStack(Integer.parseInt(items[i]));
		}
		return stack;
	}

}
