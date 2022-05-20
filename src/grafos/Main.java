package grafos;

public class Main {

	public static void main(String[] args) {
	GrafoDirigido<Integer> g1 = new GrafoDirigido<Integer>();
	GrafoNoDirigido<Integer> g2 = new GrafoNoDirigido<Integer>();
	Algoritmo a1 = new Algoritmo(g1);
	Algoritmo a2 = new Algoritmo(g2);
	
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
	System.out.println(a1.verticesQueLlegan(5));
	System.out.println(a1.caminoAlternativo(1, 5, 4));
	System.out.println(a1.caminoMasCorto(1, 7));
	
	/************************************************/
	
	g2.agregarVertice(1);
	g2.agregarVertice(2);
	g2.agregarVertice(3);
	g2.agregarVertice(4);
	g2.agregarVertice(5);
	g2.agregarVertice(7);
	
	g2.agregarArco(1, 2, 0);//tandil--mdp
	g2.agregarArco(1, 3, 1);//tandil--azul
	g2.agregarArco(2, 5, 4);//mdp--bsas
	g2.agregarArco(3, 5, 5);//azul--bsas
	g2.agregarArco(3, 4, 5);//azul--flores
	g2.agregarArco(3, 6, 6);//azul--rauch
	g2.agregarArco(4, 6, 7);//flores--rauch
	g2.agregarArco(5, 7, 8);//bsas--la plata
	g2.agregarArco(5, 6, 9);//bsas--rauch
	g2.agregarArco(7, 1, 10); //la plata--tandil
	
	Arco aa = new Arco(4,6,7);
	
	System.out.println(a2.rutasAlternativas(5, 1, aa));
	}
	
	
	//crear arcos de tareas

}
