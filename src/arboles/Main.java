package arboles;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Arbol a1 = new Arbol(8);
		ArrayList<Integer> list1 = new ArrayList<Integer>();

		list1.add(15);
		list1.add(4);
		list1.add(1);
		list1.add(25);
		list1.add(50);
		list1.add(6);
		list1.add(7);
		list1.add(20);
		list1.add(5);
		list1.add(30);
		Arbol a2 = new Arbol(list1);
		
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
		
		System.out.println("Esta el num 7: " + a1.hasElement(7));
		System.out.println("Esta el num 5: " + a1.hasElement(5));	
		System.out.println("El elemento más grande: " + a1.getMaxElem());
		System.out.println("Altura del arbol: " + a1.getHeight());
		System.out.println("La rama más larga es: " + a1.getLongestBranch());
		System.out.println("Las hojas del árbol son: " + a1.getFrontera());
		System.out.println("Los elementos del nivel son: " + a1.getElemAtLevel(2));
		
		/*Ejercicio extra entregable del año 2021*/
		System.out.println("Arbol 2");
		
		System.out.println("Esta el num 7: " + a2.hasElement(7));
		System.out.println("Esta el num 5: " + a2.hasElement(5));	
		System.out.println("El elemento más grande: " + a2.getMaxElem());
		System.out.println("Altura del arbol: " + a2.getHeight());
		System.out.println("La rama más larga es: " + a2.getLongestBranch());
		System.out.println("Las hojas del árbol son: " + a2.getFrontera());
		System.out.println("Los elementos del nivel son: " + a2.getElemAtLevel(2));
		
	}

}
