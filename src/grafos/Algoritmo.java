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
			arcos.addAll(ej6Visitar(verticeId, destino));
		
		return arcos;
		
	}
		
	public ArrayList<Integer> ej6Visitar(int vertice, int destino) {
		ArrayList<Integer> verticesConCamino = new ArrayList<>();
		ArrayList<Integer> aux = new ArrayList<>();
		Boolean encontro = false;
		if(vertice==destino) {
			verticesConCamino.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			
			while(it.hasNext() && !encontro) {
				int adyacente = it.next();
				if (colores.get(adyacente).equals("blanco")) {
					aux = ej6Visitar(adyacente,destino);
					if(!aux.isEmpty()) {
						verticesConCamino.add(vertice);
						verticesConCamino.addAll(aux);
						encontro = true;
					}
				}
			}
		}
		return verticesConCamino;
	}
	
	//Ejercicio 7.
		public ArrayList<Integer> caminoMasCorto(int origen, int destino){
		ArrayList<Integer> arcos = new ArrayList<>();
					
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		int vertice = origen;
		arcos.addAll(ej7visita(vertice, destino));
		
		return arcos;
		
	}
		
	public ArrayList<Integer> ej7visita(int vertice, int destino) {
		ArrayList<Integer> caminoPosible = new ArrayList<>();
		ArrayList<Integer> caminoAdyacente = new ArrayList<>();	
		if(vertice==destino) {
			caminoPosible.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			
			while(it.hasNext()) {
				int adyacente = it.next();
				if (colores.get(adyacente).equals("blanco")) {
					
					caminoAdyacente.add(vertice);				
					caminoAdyacente.addAll(ej7visita(adyacente, destino));
					
					if(caminoPosible.isEmpty()) {
						caminoPosible.addAll(caminoAdyacente);
					}else if(caminoPosible.size()>=caminoAdyacente.size()) {
						caminoPosible.clear();
						caminoPosible.addAll(caminoAdyacente);
						
					}
				}
			}
		}
		
		
		return caminoPosible;
	}
	
	/*Ejercicio 8
	Dados un grafo G con sus vértices rotulados con colores y dos vértices v1 y v2, escriba un
	algoritmo que encuentre un camino desde el vértice v1 al vértice v2 tal que no pase por
	vértices rotulados con el color rojo.
	*/
	
	
	/*Ejercicio 9
	Dado un grafo no orientado que modela las rutas de la provincia de Buenos Aires, devolver
	todos los caminos alternativos que se pueden tomar para ir desde la ciudad de Buenos
	Aires a la ciudad de Tandil, considerando que en el tramo Las Flores-Rauch está cortado al
	tránsito.
	*/
	
	public ArrayList<Integer> rutasAlternativas(int origen, int destino, Arco<?> corte) {
		ArrayList<Integer> rutas = new ArrayList<>();

		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		
		int cortado = corte.getVerticeOrigen();
		
		int vertice = origen;
		if (colores.get(vertice).equals("blanco"))
			rutas.addAll(ej9visita(vertice, origen, destino, cortado));
		
		return rutas;
	}
	
	public ArrayList<Integer> ej9visita(int vertice, int origen, int destino, int cortado) {
		ArrayList<Integer> rutasPosibles = new ArrayList<>();
		if(vertice == destino) {
			rutasPosibles.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
	
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			while(it.hasNext()) {
				int adyacente = it.next();
				if(adyacente != cortado) {
					if (colores.get(adyacente).equals("blanco"))
						rutasPosibles.add(vertice);
						rutasPosibles.addAll(ej9visita(adyacente, origen, destino, cortado));
				}
			}
		}
		
		return rutasPosibles;
	}
	
	
	
	
	
	
	
	
	
}
