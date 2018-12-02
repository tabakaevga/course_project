import java.util.ArrayList;
import java.util.List;
import VectorAndStack.Vector;

//Временное консольное приложение дл¤ теста функциональности
public class ConsoleApp {
	public static void main (String[] args)
	{
		List<Integer> controlGroupJoin = new ArrayList<Integer>();
		controlGroupJoin.add(2);
		controlGroupJoin.add(-5);
		controlGroupJoin.add(72);
		List<Integer> controlGroupCross = new ArrayList<Integer>();
		controlGroupCross.add(-5);
		Vector vector = new Vector();
		vector.operation("[2,-5] [-5,72] +");
		List<Integer> join = vector.vector;
		System.out.println("Join check \n [2,-5] [-5,72] +");
		for (int i = 0; i < join.toArray().length; i++)
		{
			System.out.println(join.toArray()[i] == controlGroupJoin.toArray()[i]);
		}
		vector.operation("[2,-5,72] [-5] *");
		List<Integer> cross = vector.vector;
		System.out.println("Cross check \n [2,-5,72] [-5] *");
		for (int i = 0; i < cross.toArray().length; i++)
		{
			System.out.println(cross.toArray()[i] == controlGroupCross.toArray()[i]);
		}
		
		System.out.println("IsIn check \n [2,-5,72] -5 ? \n" + vector.operation("[2,-5,72] -5 ?"));
		
	}

}