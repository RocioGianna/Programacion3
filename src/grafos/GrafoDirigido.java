package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<Integer, ArrayList<Arco<T>>> vertices;
	
	
	public GrafoDirigido(){
		this.vertices = new HashMap<Integer,ArrayList<Arco<T>>>();
	}
	
	@Override
	public void agregarVertice(int verticeId) { //Complejidad O(1)
		ArrayList<Arco<T>> vertice = new ArrayList<Arco<T>>();
		if(!vertices.containsKey(verticeId)) {
			vertices.put(verticeId, vertice);
		}

	}

	@Override //Complejidad O(1)
	public void borrarVertice(int verticeId) {
		if(vertices.containsKey(verticeId)) {
			vertices.get(verticeId).clear();
			vertices.remove(verticeId);
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(vertices.containsKey(verticeId1)) {
			if(!existeArco(verticeId1, verticeId2)) {
				Arco<T> a = new Arco<T>(verticeId1, verticeId2, etiqueta);
				vertices.get(verticeId1).add(a);
			}
		}else {
			agregarVertice(verticeId1);
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(existeArco(verticeId1, verticeId2)) {
			ArrayList<Arco<T>> aux = vertices.get(verticeId1);
			for(Arco<T> a:aux){
				if(a.getVerticeDestino() == verticeId2) {
					aux.remove(a);
				}
			}
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
			ArrayList<Arco<T>> aux = vertices.get(verticeId1);
			for(Arco<T> a:aux){
				if(a.getVerticeDestino() == verticeId2) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Arco<T> arco = null;
		if(existeArco(verticeId1, verticeId2)) {
			ArrayList<Arco<T>> aux = vertices.get(verticeId1);
			for(Arco<T> a:aux){
				if(a.getVerticeDestino() == verticeId2) {
					 arco = a;
				}
			}
		}
		return arco;
	}

	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int aux = 0;
		for(int i:vertices.keySet()) {
			aux += vertices.get(i).size();
		}
		return aux;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Arco<T>> adyacentes = vertices.get(verticeId);
		ArrayList<Integer> aux = new ArrayList<>();
		for(Arco<T> a:adyacentes) {
			aux.add(a.getVerticeDestino());
		}
		return aux.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcos = new ArrayList<>();
		for(int i:vertices.keySet()) {
			arcos.addAll(vertices.get(i));
		}
		return arcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return vertices.get(verticeId).iterator();
	}

}