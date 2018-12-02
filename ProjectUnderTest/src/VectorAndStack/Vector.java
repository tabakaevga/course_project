package VectorAndStack;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vector 
{
	public ArrayList<Integer> vector;
	
	public Vector()
	{
	}
	
	private String arrayRegex = "^\\[([-]?\\d+(,\\s*[-]?\\d+)*)?\\]\\s*";
	
	
	//–егул€рные выражени€ дл€ операций над вектором
	private String[] operationsRegex = 
		{
			arrayRegex + arrayRegex.replace("^", "") + "[+]$",	//ќбъединение векторов
			arrayRegex + "[-]?\\d+\\s*[?]$", 					//¬хождение числа в вектор
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
		List<Integer> vectorLeft = parseStringToMyStack(matcher.group(0));
		Integer number = Integer
				.parseInt(inputString.replaceAll(arrayRegex, "").replaceAll("\\s*[?]", ""));
		return vectorLeft.contains(number);
	}

	private boolean joinVectors(String inputString)
	{
		Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
		Matcher matcher = pattern.matcher(inputString);
		matcher.find();
		int endOfFirst = matcher.end();
		ArrayList<Integer> vectorLeft = parseStringToMyStack(matcher.group(0));
		matcher.find(endOfFirst);
		List<Integer> vectorRight = parseStringToMyStack(matcher.group(0));
		for (Integer number : vectorRight)
		{
			if (!vectorLeft.contains(number))
			{
				vectorLeft.add(number);
			}
		}
		vector = vectorLeft;
		return false;
	}
	
	private boolean crossVectors(String inputString)
	{
		Pattern pattern = Pattern.compile(arrayRegex.replace("^", ""));
		Matcher matcher = pattern.matcher(inputString);
		matcher.find();
		int endOfFirst = matcher.end();
		List<Integer> vectorLeft = parseStringToMyStack(matcher.group(0));
		matcher.find(endOfFirst);
		List<Integer> vectorRight = parseStringToMyStack(matcher.group(0));
		ArrayList<Integer> newVector = new ArrayList<Integer>();
		for (Integer number : vectorRight)
		{
			if (vectorLeft.contains(number))
			{
				newVector.add(number);
			}
		}
		vector = newVector;
		return false;
	}
	
	private ArrayList<Integer> parseStringToMyStack(String stringToParse)
	{
		String[] items = stringToParse.replaceAll("\\[", "").replaceAll("\\]", "")
				.replaceAll("\\s", "").split(",");
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < items.length; i++) {
		    numbers.add(Integer.parseInt(items[i]));
		}
		return numbers;
	}

}
