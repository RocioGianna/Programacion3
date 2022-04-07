
public class NodeD<T>{
	private NodeD<T> anterior;
	private NodeD<T> next;
	private Object info;

	public NodeD() {
		this.info = 0;
		this.next = null;
		this.anterior = null;
	}
	
	public NodeD(Object info, NodeD<T> next, NodeD<T> ant) {
		this.setInfo(info);
		this.setNext(next);
		this.setAnterior(ant);
	}
	
	public NodeD<T> getNext() {
		return next;
	}

	public void setNext(NodeD<T> next) {
		this.next = next;
	}
	
	public NodeD<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(NodeD<T> ant) {
		this.anterior = ant;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
}
