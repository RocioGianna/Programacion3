package arboles;

import java.util.ArrayList;
import java.util.List;

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
	
	/* Integer getRoot(), boolean hasElem(Integer), boolean isEmpty(), void insert(Integer),
	 * void printPosOrder(), void printPreOrder(), void	printInOrder(), Integer getMaxElem(),
	 * int getHeight(), List getLongestBranch(), List getFrontera(), List getElemAtLevel(int),
	 * 
	   boolean delete(Integer)  
	*/
	
	public Boolean hasElement(int x) { //Complejidad O(n)
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
	
	public void insert(int i) { //Complejidad O(n)
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
	
	public void printPosOrder(Arbol n) { //Complejidad O(n)
		if(n != null) {
			printPosOrder(n.getMenor());
			printPosOrder(n.getMayor());
			System.out.println(n.getInfo());
		}
	}
	
	public void printPreOrder(Arbol n) { //Complejidad O(n)
		if(n != null) {
			System.out.println(n.getInfo());
			printPreOrder(n.getMenor());
			printPreOrder(n.getMayor());
		}
	}
	
	public void printInOrder(Arbol n) { //Complejidad O(n)
		if(n != null) {
			printInOrder(n.getMenor());
			System.out.println(n.getInfo());
			printInOrder(n.getMayor());
		}
	}
	
	public int getMaxElem() {
		int max = this.info;
		if( this.mayor != null && max < this.getMayor().getInfo()) {
			max = this.getMayor().getMaxElem();
		}
		return max;
	}
	
	public int getHeight() {
		int heightIzq = 0;
		int heightDer = 0;
		
		if(this.menor != null) {
			heightIzq = 1 + this.getMenor().getHeight();
		}
		if(this.mayor != null) {
			heightDer = 1 + this.getMayor().getHeight();
		}
		
		if(heightIzq > heightDer) {
			return heightIzq;
		}else if(heightIzq < heightDer){
			return heightDer;
		}else {
			return heightDer;			
		}
	}
	
	public ArrayList<Integer> getLongestBranch() {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		if(menor != null) {
			list1.add(this.getInfo());
			list2.add(this.getInfo());
			
			list1.addAll(menor.getLongestBranch());
			
			if(mayor != null) {
				list2.addAll(mayor.getLongestBranch());
			}
		}else if(mayor != null) {
			list1.add(this.getInfo());
			list1.addAll(mayor.getLongestBranch());
		}else {
			list1.add(this.getInfo());
		}
		
		if(list1.size() > list2.size()) {
			return list1;
		}else {
			return list2;
		}
		
	}
	
	public ArrayList<Integer> getFrontera(){ //Complejidad O(n)
		ArrayList<Integer> aux = new ArrayList<Integer>();
		
		if(menor == null && mayor == null) {
			aux.add(this.info);
		}
		if(menor != null) {
			aux.addAll(getMenor().getFrontera());
		}
		if(mayor != null) {
			aux.addAll(getMayor().getFrontera());
		}

		return aux;
		
	}
	
	public ArrayList<Integer> getElemAtLevel(int lvl){ //Complejidad O(n)
		ArrayList<Integer> aux = new ArrayList<Integer>();
		int nivel = 0;
		
		if(nivel != lvl) {
			if(menor != null) {
				aux.addAll(getMenor().getElemAtLevel(lvl - 1));
			}
			if(mayor != null) {
				aux.addAll(getMayor().getElemAtLevel(lvl - 1));
			}
		}else {
			aux.add(this.info);
		}
		
		return aux;
	}
	
	
	
	//getters y setters
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
