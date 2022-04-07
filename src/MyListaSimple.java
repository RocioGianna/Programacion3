import java.util.Iterator;

public class MyListaSimple<T> implements Iterable<Object>{
	private Node<T> first;
	private static int size = 0;
	
	public MyListaSimple() {
		this.first = null;
	}
	
	public Node getFirst() {
		return this.first;
	}
	
	public void insertFront(int info) {
		Node<T> tmp = new Node<T>(info,null);
		tmp.setNext(this.first);
		this.first = tmp;
		size++;
	}
	
	/*Extrae el primero de la lista y devuelve la info del mismo*/
	public int extractFront() {		
		Node<T> tmp = first;
		if(first != null && first.getNext() != null) {
			first = tmp.getNext();
			size--;		
		}
		
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		if(first == null) {
			return true;
		}
		return false;
	}
	
	/*Dado un indice retorna lo que hay dentro del mismo en caso que tenga algo*/
	public int get(int index) { 
		if(first != null && this.size() >= index) {
			Node<T> tmp = first;
			for(int i = 0; i <= index; i++) {
				tmp.getNext();
			}	
			return tmp.getInfo();
		}
		return -1;
	}
	
	public int size() {
		return this.size;
	}
	
	
	public void String() {
			Iterator<Object> it = this.iterator();
			Node tmp = first;
			while(it.hasNext()) {
				Object info = it.next();
				System.out.println(info);
			}
	}
	
	/*Método que retorna una nueva lista con la información que tienen en común (sin repetir)*/
	public MyListaSimple<Node> getComunesOrdenados(MyListaSimple<Node> l1, MyListaSimple<Node> l2){
		MyListaSimple<Node> tmp = new MyListaSimple<Node>();
		Iterator<Object> it = l1.iterator();
		Node<T> it2 =  l2.getFirst();
		//Iterator<Object> it2 = l2.iterator();
		Object i = null;
		for(Object o:l1) {
			System.out.println(o);
		}
		
		
		while(it.hasNext()) {
			i = it.next();
			while(it2 != null) {
				Object i2 = it2.getInfo();
				if( i.equals(i2)) {
					tmp.insertFront((int) i);
				}
				it2 = it2.getNext();
			}
			 it2 = l2.getFirst();
		}
	
		return tmp;
	}
	/*
	 * Iterador del primero que tengo que recorrerlo de a uno.

		Un while que avance el segundo porque siempre tengo que volver 
		al principio ya que las listas no estan ordenadas
	 */
	
	@Override
	public Iterator<Object> iterator() {
		return new Iterador(first);
	}


	
	
	
	
}
