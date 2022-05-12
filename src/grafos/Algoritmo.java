package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Algoritmo {
	private Grafo<?> grafo;
	private int tiempo;	
	private HashMap<Integer,String> colores;
	private HashMap<Integer,Integer> descubrimiento;
	private HashMap<Integer,Integer> finalizacion;
	private Boolean existe = false;
	ArrayList<Integer> aux = new ArrayList<>();

	public Algoritmo(Grafo<?> grafo) {
		this.grafo = grafo;
		this.colores = new HashMap<>();
		this.descubrimiento = new HashMap<>();
		this.finalizacion = new HashMap<>();
		this.tiempo = 0;
	}
	
	//Ejercicio 3
	public Boolean existeCiclo() {
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		this.tiempo = 0;
		
		it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			if (colores.get(verticeId).equals("blanco"))
				existe = dfs_visit(verticeId);
		}
		
		return existe;
	}
	
	public Boolean dfs_visit(int vertice) {
		colores.put(vertice, "amarillo");
		tiempo += 1;
		descubrimiento.put(vertice, tiempo);
		
		Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
		while(it.hasNext() && !existe) {
			int adyacente = it.next();
			if (colores.get(adyacente).equals("blanco")) {
				existe = dfs_visit(adyacente);				
			}else if (colores.get(adyacente).equals("amarillo")) {
				return true;
			}
		}
		
		colores.put(vertice, "negro");
		tiempo += 1;
		finalizacion.put(vertice, tiempo);
		
		return existe;
	}
	/*Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que
el grafo de entrada es acíclico*/
	public ArrayList<Integer> caminoMasLargo(int origen, int destino){
		ArrayList<Integer> arcos = new ArrayList<>();
					
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		int vertice = origen;
		if (colores.get(vertice).equals("blanco"))
			arcos.addAll(visita(vertice, destino, origen));
		
		return arcos;
		
	}
		
	public ArrayList<Integer> visita(int vertice, int destino, int origen) {
		ArrayList<Integer> caminoPosible = new ArrayList<>();
		if(vertice==destino) {
			caminoPosible.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			
			while(it.hasNext()) {
				int adyacente = it.next();
				if (colores.get(adyacente).equals("blanco")) {
					ArrayList<Integer> caminoAdyacente = new ArrayList<>();									
					caminoAdyacente.addAll(visita(adyacente, destino, origen));
					if(caminoPosible.size()<=caminoAdyacente.size()) {
						caminoPosible.clear();
						caminoPosible.add(vertice);
						caminoPosible.addAll(caminoAdyacente);
					}
				}
			}
			
			colores.put(vertice, "blanco");
		}
		
		
		return caminoPosible;
	}

	
	
	
}
