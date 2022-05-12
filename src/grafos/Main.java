package grafos;

public class Main {

	public static void main(String[] args) {
	GrafoDirigido<Integer> g1 = new GrafoDirigido<Integer>();
	Algoritmo a1 = new Algoritmo(g1);
	
	g1.agregarVertice(1);
	g1.agregarVertice(2);
	g1.agregarVertice(3);
	g1.agregarVertice(4);
	g1.agregarVertice(5);
	g1.agregarVertice(7);
	
	g1.agregarArco(1, 2, 0);
	g1.agregarArco(1, 3, 1);
	g1.agregarArco(1, 4, 3);
	g1.agregarArco(2, 5, 4);
	g1.agregarArco(4, 7, 5);
	//g1.agregarArco(3, 1, 5);
	g1.agregarArco(5, 3, 6);
	g1.agregarArco(7, 5, 7);
	
	
	System.out.println(a1.existeCiclo());
	System.out.println(a1.caminoMasLargo(1, 5));
	
	
	
	
	}

}
