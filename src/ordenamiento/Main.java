package ordenamiento;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Ordenamiento o = new Ordenamiento();
		
		arr.add(8);
		arr.add(3);
		arr.add(5);
		arr.add(1);
		arr.add(12);
		arr.add(66);
		
		o.imprimir(arr);
		//o.seleccion(arr);
		o.burbujeo(arr);
		o.imprimir(arr);
		

	}

}
