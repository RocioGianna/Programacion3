package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BFS {
	private Grafo<?> grafo;
	private HashMap<Integer,Boolean> visitado;
	private ArrayList<Integer> vertices = new ArrayList<>();
	
	
	public BFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitado = new HashMap<>();
	}
	
	public void bfs() {
		vertices.clear();
		Iterator<Integer> it = this.grafo.obtenerVertices();
		
		while (it.hasNext()) {
			int verticeId = it.next();
			visitado.put(verticeId, false);
		}
		
		while (it.hasNext()) {
			int verticeId = it.next();
			if (!visitado.get(verticeId)) {
				bfs(verticeId);
			}
		}
	}
	
	public void bfs(int vertice) {
		visitado.put(vertice, true);
		vertices.add(vertice);
		
		while(!vertices.isEmpty()) {
			int first = vertices.get(0);
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(first);
			while(it.hasNext()) {
				int indice = it.next();
				if(!visitado.get(indice)) {
					visitado.put(indice, true);
					vertices.add(indice);
					System.out.println(vertices);
				}
			}	
			vertices.remove(first);
		}
		
	}
	
}
