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
	private HashMap<Integer,Boolean> visitado;
	private ArrayList<Integer> vertices = new ArrayList<>();
	private HashMap<Integer,Tarea> tareas;

	public Algoritmo(Grafo<?> grafo) {
		this.grafo = grafo;
		this.colores = new HashMap<>();
		this.descubrimiento = new HashMap<>();
		this.finalizacion = new HashMap<>();
		this.visitado = new HashMap<>();
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
	
	//Ejercicio 6.
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
	
	/*public ArrayList<Integer> caminoMasCorto(int origen, int destino) {
		vertices.clear();
		Iterator<Integer> it = this.grafo.obtenerVertices();
		
		while (it.hasNext()) {
			int verticeId = it.next();
			visitado.put(verticeId, false);
		}
		
		int verticeId = origen;
		if (!visitado.get(verticeId)) {
			bfs_visit(verticeId, destino);
		}
		return vertices;
	}
	
	public ArrayList<Integer> bfs_visit(int vertice, int destino) {
		visitado.put(vertice, true);
		vertices.add(vertice);
		
		while(!vertices.isEmpty()) {
			System.out.println(vertices);
			int first = vertices.remove(0);
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(first);
			while(it.hasNext()) {
				int indice = it.next();
				if(!visitado.get(indice)) {
					visitado.put(indice, true);
					vertices.add(indice);
				}
			}	
		}
		return vertices;
	} */
	
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
					if(!caminoAdyacente.isEmpty()) {
						if(caminoAdyacente.contains(destino)) {
							if(caminoPosible.isEmpty()) {
								caminoPosible.addAll(caminoAdyacente);
							}else if(caminoPosible.size()>=caminoAdyacente.size()) {
								caminoPosible.clear();
								caminoPosible.addAll(caminoAdyacente);
								
							}
						}else {
							caminoAdyacente.clear();
						}
					}
				}
			}
			colores.put(vertice, "blanco");
		}
		
		
		return caminoPosible;
	}
	
	
	/*Ejercicio 8
	Dados un grafo G con sus vértices rotulados con colores y dos vértices v1 y v2, escriba un
	algoritmo que encuentre un camino desde el vértice v1 al vértice v2 tal que no pase por
	vértices rotulados con el color rojo.
	*/
	
	
	//Ejercicio 9
	public ArrayList<Integer> rutasAlternativas(int origen, int destino, Arco<?> corte) {
		ArrayList<Integer> rutas = new ArrayList<>();

		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		
		int vertice = origen;
		if (colores.get(vertice).equals("blanco"))
			rutas.addAll(ej9visita(vertice, destino, corte));
		
		return rutas;
	}
	
	public ArrayList<Integer> ej9visita(int vertice, int destino, Arco<?> corte) {
		ArrayList<Integer> rutasPosibles = new ArrayList<>();
		if(vertice == destino) {
			rutasPosibles.add(vertice);
		}else {
			colores.put(vertice, "amarillo");
	
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
			while(it.hasNext()) {
				int adyacente = it.next();
				if(adyacente != corte.getVerticeDestino()) {
					if (colores.get(adyacente).equals("blanco")) {
						rutasPosibles.add(vertice);
						rutasPosibles.addAll(ej9visita(adyacente, destino, corte));
					}
				}
			}
			colores.put(vertice, "blanco");
		}
		
		return rutasPosibles;
	}
	
	/*Ejercicio 10
	Se dispone de un conjunto de tareas, donde cada tarea tiene un nombre, una descripción y
	una duración (medida en horas). Se sabe también que hay una dependencia en el orden
	posible en el cual se pueden ejecutar estas tareas y un tiempo de espera entre dos tareas
	consecutivas (también medido en horas). Por ejemplo, si la tarea B depende de la tarea A y
	tiene un tiempo de espera de 5 horas; significa que:
	● B no puede ejecutarse antes que A y,
	● B debe ejecutarse 5 horas después de haber finalizado la ejecución de A.
	Objetivo
	Implementar un algoritmo que obtenga la secuencia de ejecución crítica de estas tareas, es
	decir, la secuencia de tareas que resulta en el máximo tiempo empleado para su ejecución.
	Por ejemplo: si partimos de la siguiente configuración podemos encontrar el camino crítico
	en la secuencia de tareas [0, 2, 5, 6, 10], ya que su tiempo de ejecución es la duración de
	cada tarea más el tiempo de espera entre cada par de tareas: 70 horas.*/
	
	public void secuenciaTareas() {
		
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
				ej10visit(verticeId);
		}
		
	}
	
	public void ej10visit(int vertice) {
		
		colores.put(vertice, "amarillo");
		tiempo += 1;
		descubrimiento.put(vertice, tiempo);
		
		Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
		while(it.hasNext()) {
			int adyacente = it.next();
			if (colores.get(adyacente).equals("blanco"))
				ej10visit(adyacente);
		}
		
		colores.put(vertice, "negro");
		tiempo += 1;
		finalizacion.put(vertice, tiempo);
		
	}
	
	public void addTareas(Tarea tareas){
		this.tareas.put(tareas.getId(), tareas);
		this.grafo.agregarVertice(tareas.getId());
		this.colores.put(tareas.getId(), "blanco");
	}
	
	
	
}
