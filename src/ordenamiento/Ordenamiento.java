package ordenamiento;

import java.util.ArrayList;

public class Ordenamiento {

	
	public Ordenamiento() {}
	
	
	public void seleccion(ArrayList<Integer> arr){
		int aux = 0;
		for(int i = 0; i < arr.size() - 1; i++) {
			for(int j = i + 1; j < arr.size(); j++) {
				if(arr.get(i) > arr.get(j)) {
					aux = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, aux);
				}
			}
		}
	}
	public void burbujeo(ArrayList<Integer>arr){
		int aux = 0;
		for(int i = 0; i < arr.size(); i++) {
			for(int j = 0; j < arr.size() - 1; j++) {
				if(arr.get(j) > arr.get(j + 1)) {
					aux = arr.get(j);
					arr.set(j, arr.get(j + 1));
					arr.set(j + 1, aux);
				}
			}
		}
	}
	public void quicksort(ArrayList<Integer> arr, int izquierda, int derecha){}
	public void mergesort(ArrayList<Integer> arr){}
	
	public void imprimir(ArrayList<Integer> arr) {
		System.out.println("***********************");
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
}
