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
	//Ejercicio 4
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
	
	//Ejercicio 5
	public ArrayList<Integer> verticesQueLlegan(int destino){
		ArrayList<Integer> arcos = new ArrayList<>();
					
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		
		it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			if (colores.get(verticeId).equals("blanco"))
				arcos.addAll(ej5Visitar(verticeId, destino));
		}	
			return arcos;
		
	}
		
	public ArrayList<Integer> ej5Visitar(int vertice, int destino) {
		ArrayList<Integer> verticesConCamino = new ArrayList<>();
		if(vertice != destino) {
			colores.put(vertice, "amarillo");
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			
			while(it.hasNext()) {
				int adyacente = it.next();
				if (colores.get(adyacente).equals("blanco")) {								
					verticesConCamino.addAll(ej5Visitar(adyacente, destino));
					if(!verticesConCamino.contains(vertice)) {
						verticesConCamino.add(vertice);
					}
				}
			}
		}
		
		
		return verticesConCamino;
	}
	
	/*Ejercicio 6.
	Supongamos una conexión entre computadoras (1, ... ,n) que se encuentra modelada
	mediante un grafo. Se requiere, si existe, dar una conexión entre dos computadoras a y b
	existentes sabiendo que la computadora i está fuera de servicio.*/
	public ArrayList<Integer> caminoAlternativo(int origen, int destino, int pcRota){
		ArrayList<Integer> arcos = new ArrayList<>();
					
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			if(verticeId == pcRota) {
				colores.put(verticeId, "negro");
			}else {
				colores.put(verticeId, "blanco");
			}
		}
		
		int verticeId = origen;
		if (colores.get(verticeId).equals("blanco"))
			arcos.addAll(ej6Visitar(verticeId, origen, destino));
		
		return arcos;
		
	}
		
	public ArrayList<Integer> ej6Visitar(int vertice, int origen,  int destino) {
		ArrayList<Integer> verticesConCamino = new ArrayList<>();
		if(vertice==destino && !verticesConCamino.contains(vertice)) {
			verticesConCamino.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			
			while(it.hasNext() && !colores.get(origen).equals("amarillo")) {
				int adyacente = it.next();
				if (colores.get(adyacente).equals("blanco")) {								
					verticesConCamino.addAll(ej6Visitar(adyacente, origen, destino));
					if(!verticesConCamino.contains(vertice)) {
						verticesConCamino.add(vertice);
					}
				}
			}
		}
		
		
		return verticesConCamino;
	}
	
	
	
}
