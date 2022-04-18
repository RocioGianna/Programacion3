package arboles;

public class Arbol {
	 private int info;
	 private Arbol menor;
	 private Arbol mayor;
	 
	public Arbol(int info) {
		this.info = info;
		this.menor = null;
		this.mayor = null;
	}
	
	public int getRoot() {
		return this.info;
	}
	
	public Boolean isEmpty() {
		return this == null;
	}
	
	public Boolean hasElement(int x) {
		
		if(x > this.info && this.mayor != null) {
			return this.getMayor().hasElement(x);
		}else if(x < this.info && this.menor != null) {
			return this.getMenor().hasElement(x);
		}else if(x == this.info){
			return true;			
		}else {
			return false;
	    }
	}
	
	public void insert(int i) {
		if(i > this.info) {
			if(this.getMayor() != null) {
				this.getMayor().insert(i);
			} else {
				this.mayor = new Arbol(i);
			}
		}
		if(i < this.info) {
			if(this.getMenor() != null) {
				this.getMenor().insert(i);
			} else {
				this.menor = new Arbol(i);
			}
		}
	}
	
	public void printPosOrder(Arbol n) {
		if(n != null) {
			printPosOrder(n.getMenor());
			printPosOrder(n.getMayor());
			System.out.println(n.getInfo());
		}
	}
	
	public void printPreOrder(Arbol n) {
		if(n != null) {
			System.out.println(n.getInfo());
			printPreOrder(n.getMenor());
			printPreOrder(n.getMayor());
		}
	}
	
	public void printInOrder(Arbol n) {
		if(n != null) {
			printInOrder(n.getMenor());
			System.out.println(n.getInfo());
			printInOrder(n.getMayor());
		}
	}
	
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public Arbol getMenor() {
		return menor;
	}
	public void setMenor(Arbol menor) {
		this.menor = menor;
	}
	public Arbol getMayor() {
		return mayor;
	}
	public void setMayor(Arbol mayor) {
		this.mayor = mayor;
	}
	 
	 
	 
	 
	 
	 
}
