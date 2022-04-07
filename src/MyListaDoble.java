import java.util.Iterator;

public class MyListaDoble<T> implements Iterable<Object>{
	private NodeD<T> first;
	private NodeD<T> last;
	private int size;
	
	public MyListaDoble() {
		this.first = null;
		this.size = 0;
	}
	
	public NodeD<T> getFirst() {
		return this.first;
	}
	public NodeD<T> getLast() {
		return this.last;
	}
	
	public void insertFront(Object info) {
		NodeD<T> tmp = new NodeD<T>(info, null, null);
		if(isEmpty()) {
			first.setAnterior(tmp);
			tmp.setNext(this.first);
			last = first;
			this.first = tmp;			
		}else {
			tmp.setNext(this.first);
			this.first = tmp;	
			last = first;
		}
		size++;
	}
	
	/*Extrae el primero de la lista y devuelve la info del mismo*/
	public Object extractFront() {		
		NodeD<T> tmp = first;
		if(isEmpty() && first.getNext() != null) {
			first = tmp.getNext();	
		}
		size--;
		return tmp.getInfo();
	}

	public boolean isEmpty() {
		return first != null;
	}
	
	public int size() {
		return size;
	}
	
	/*Dado un indice retorna lo que hay dentro del mismo en caso que tenga algo*/
	public Object get(int index) { 
		if(first != null && this.size() >= index) {
			NodeD<T> tmp = first;
			for(int i = 0; i <= index; i++) {
				tmp.getNext();
			}	
			return tmp.getInfo();
		}
		return -1;
	}
	
	public void String() {
		Iterator<Object> it = this.iterator();
		NodeD<T> tmp = first;
		while(it.hasNext()) {
			Object info = it.next();
			System.out.println(info);
		}
	}
	
	/*Método que retorna una nueva lista con la información que tienen en común*/
	public MyListaSimple<Node> getComunesOrdenados(MyListaSimple<Node> l1, MyListaSimple<Node> l2){
		MyListaSimple<Node> tmp = new MyListaSimple<Node>();
		Iterator<Object> it = l1.iterator();
		Node<T> it2 =  l2.getFirst();
		Object i = it.next();
		
		while(it.hasNext()) {
			while(it2 != null) {
				Object i2 = it2.getInfo();
				if( i.equals(i2)) {
					tmp.insertFront((int) i);
				}
				it2 = it2.getNext();
			}
			 i = it.next();
			 it2 = l2.getFirst();
		}
	
		return tmp;
	}
	
	public Boolean isCapicua(){
		Iterador it1 = new Iterador(first);
		NodeD it2 = this.last;
		int cont = 0;
		
		while(it1.hasNext() && it2 != null && cont != (this.size() / 2)) {
			if(it1.getInfo().equals(it2.getInfo())) {
				it1.next();
				it2.getAnterior();
				cont++;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new Iterador(first);
	}

	
	
	
}
