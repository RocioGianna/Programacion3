
public class Node<T> {
	private int info;
	private Node<T> next;

	public Node() {
		this.info = 0;
		this.next = null;
	}
	
	public Node(int info, Node<T> next) {
		this.setInfo(info);
		this.setNext(next);
	}
	
	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}
}
