import java.util.Iterator;

public class Iterador implements Iterator<Object>{
	
	private NodeD index;
	
	public Iterador(NodeD first) {
		this.index = first;
	}

	@Override
	public boolean hasNext() {
		return index != null;
	}
	
	public Object getInfo() {
		return index.getInfo();
	}

	@Override
	public Object next() {
		Object tmp = index.getInfo();
		index = index.getNext();
		return tmp;
	}
}
