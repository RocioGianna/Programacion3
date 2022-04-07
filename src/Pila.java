
public class Pila {
	private MyListaSimple<Node> l1;
	private static int size;
	
	public Pila() {
		this.l1 = new MyListaSimple<Node>();
	}
	
	public void push(int info) {
		l1.insertFront(info);
		size++;
	}
	
	public int top() {
		return l1.get(l1.size());
	}

	public int pop() {
		size--;
		return l1.extractFront();
	}
	
	public void String() {
		l1.String();
	}
	
	public void reverse() {
		MyListaSimple<Node> lAux = new MyListaSimple<Node>();
		
		for(int i = 0; i < this.size; i++) {
			int tmp = l1.extractFront();
			lAux.insertFront(tmp);			
		}
		
		l1 = lAux;
	}
}




