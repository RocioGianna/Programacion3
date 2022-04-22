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
	public Arbol() {
		this.info = 0;
		this.menor = null;
		this.mayor = null;
	}
	public Arbol(ArrayList<Integer> valores) {
		for(Integer v: valores) {
			insert(v);
		}
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
	 * void delete(Integer)*/
	
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
		if(this.info == 0) {
			this.info = i;
		}
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
		}else {
			System.out.println("-");
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
	
	public void delete(int i) {
		if(menor != null && this.info > i) { // Rama izquierda
			if(getMenor().getInfo() != i) { // parte recursiva avanza si no es el valor que se busca
				getMenor().delete(i);
			}else if(getMenor().getMenor() != null && getMenor().getMayor() != null) {  // pregunta si tiene dos hijos
				Arbol index = buscarMayor(getMenor());
				getMenor().setInfo(index.getInfo());
				getMenor().getMayor().delete(index.getInfo());
			}else if(getMenor().getMenor() != null) {    // pregunta si tiene un hijo menor
				this.menor = getMenor().getMenor();
			}else if(getMenor().getMayor() != null) { // pregunta si tiene un hijo mayor
				this.menor = getMenor().getMayor();
			}else { // borra
				this.menor = null;
			}
		} else if(mayor != null && this.info < i ) { // hace lo mismo que el de arriba pero por la rama derecha 
			if(getMayor().getInfo() != i) {
				getMayor().delete(i);
			}else if(getMayor().getMenor() != null && getMayor().getMayor() != null) {  
				Arbol index = buscarMayor(getMayor().getMenor());
				getMayor().setInfo(index.getInfo());
				getMayor().getMenor().delete(index.getInfo());
			}else if(getMayor().getMayor() != null) {
				this.mayor = getMayor().getMayor();
			}else if(getMayor().getMenor() != null) {
				this.mayor = getMayor().getMenor();
			}else {
				this.mayor = null;
			}
		}
		
	}  
	
	public void borrar(int i) {
		if(menor != null && this.info > i) { 
			if(getMenor().getInfo() != i) { 
				getMenor().borrar(i);
			}else if(getMenor().getMenor() != null && getMenor().getMayor() != null) { 
				Arbol index = buscarMayor(getMenor().getMenor());	
				this.menor.setInfo(index.getInfo());
				this.menor.menor.borrar(index.getInfo());
			}else if(getMenor().getMenor() != null) {    
				this.menor = getMenor().getMenor();
			}else if(getMenor().getMayor() != null) { 
				this.menor = getMenor().getMayor();
			}else { // borra
				this.menor = null;
			}
		} else if(mayor != null && this.info < i ) {  
			if(getMayor().getInfo() != i) {
				getMayor().borrar(i);
			}else if(getMayor().getMenor() != null && getMayor().getMayor() != null) {  
				Arbol index = buscarMayor(getMayor().getMenor());
				this.mayor.setInfo(index.getInfo());
				this.mayor.menor.delete(index.getInfo());
				
			}else if(getMayor().getMayor() != null) {
				this.mayor = getMayor().getMayor();
			}else if(getMayor().getMenor() != null) {
				this.mayor = getMayor().getMenor();
			}else {
				this.mayor = null;
			}
		}else {
			if( menor != null && mayor !=null &&this.info == i  ) {
				Arbol index = buscarMayor(getMenor());
				this.setInfo(index.getInfo());
				this.menor.delete(index.getInfo());
					
			}else if(getMayor() != null) {
				this.mayor = getMayor().getMayor();
			}else if(getMenor() != null) {
				this.mayor = getMayor().getMenor();
			}else {
				this.setInfo(-1); //cambiarlo a Integer para poder ponerle null
			}
		}
	}
	
	public Arbol buscarMenorDeDerecha(Arbol a) {
		Arbol aux = new Arbol();
		if(a.getMenor()!=null) {
			aux = buscarMenorDeDerecha(a.getMenor());
		}else {
			aux=a;
		}
		return aux;
	}
	
	public Arbol buscarMayor(Arbol a) {
		if(a.getMayor() != null) {
			return buscarMayor(a.getMayor());
		}else {
			return a;
		}
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
