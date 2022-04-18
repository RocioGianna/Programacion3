package arboles;

public class Main {

	public static void main(String[] args) {
		Arbol a1 = new Arbol(8);
		
		a1.insert(2);
		a1.insert(70);
		a1.insert(5);
		a1.insert(1);
		a1.insert(33);
		a1.insert(40);
		
		System.out.println("pos");
		a1.printPosOrder(a1);
		
		System.out.println("pre");
		a1.printPreOrder(a1);
		
		System.out.println("order");
		a1.printInOrder(a1);
		
		System.out.println("Esta el num " + a1.hasElement(7));
		System.out.println("Esta el num " + a1.hasElement(5));	
		System.out.println("El elemento más grande: " + a1.getMaxElem());
		System.out.println("Altura del arbol: " + a1.getHeight());
		
		System.out.println(a1.getLongestBranch());
	}

}
