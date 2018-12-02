
//Временное консольное приложение для теста функциональности
public class ConsoleApp {
	public static void main (String[] args)
	{
		MyStack controlGroupJoin = new MyStack(4);
		controlGroupJoin.pushToStack(2);
		controlGroupJoin.pushToStack(-5);
		controlGroupJoin.pushToStack(72);
		MyStack controlGroupCross = new MyStack(1);
		controlGroupCross.pushToStack(72);
		Vector vector = new Vector();
		vector.operation("[2,-5] [-5,72] +");
		MyStack join = vector.vector;
		System.out.println("Join check \n [2,-5] [-5,72] +");
		for (int i = 0; i < join.getSize(); i++)
		{
			System.out.println(join.pullFromStack() == controlGroupJoin.pullFromStack());
		}
		vector.operation("[2,-5,72] [72] *");
		MyStack cross = vector.vector;
		System.out.println("Cross check \n [2,-5,72] [72] *");
		for (int i = 0; i < cross.getSize(); i++)
		{
			System.out.println(cross.pullFromStack() == controlGroupCross.pullFromStack());
		}
		
		System.out.println("IsIn check \n [2,-5,72] -5 ? \n" + vector.operation("[2,-5,72] -5 ?"));
		
	}

}
