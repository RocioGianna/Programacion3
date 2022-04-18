package arboles;

public class Main {

	public static void main(String[] args) {
		Arbol a1 = new Arbol(8);
		
		a1.insert(2);
		a1.insert(33);
		a1.insert(5);
		
		System.out.println("pos");
		a1.printPosOrder(a1);
		
		System.out.println("pre");
		a1.printPreOrder(a1);
		
		System.out.println("order");
		a1.printInOrder(a1);
		
		System.out.println(a1.hasElement(7));
		System.out.println(a1.hasElement(5));
	}

}
