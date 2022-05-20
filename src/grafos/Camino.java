package grafos;

import java.util.ArrayList;

public class Camino {
	
	private int totalHoras;
	private ArrayList<Integer> caminos;
	
	public Camino() {
		int totalHoras=0;
		caminos = new ArrayList<>();
	}
	
	
	
	public int getTotalHoras() {
		return totalHoras;
	}



	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}



	public ArrayList<Integer> getCaminos() {
		return caminos;
	}
	public void setCaminos(ArrayList<Integer> caminos) {
		this.caminos = caminos;
	}
	
	
	
	
	
	

}
