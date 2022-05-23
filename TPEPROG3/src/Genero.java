import java.util.ArrayList;

public class Genero {
	private int indice;
	private String nombre;
	private ArrayList<Libro> libros;
	
	public Genero(String nombre, int indice) {
		this.nombre = nombre;
		this.indice = indice;
		this.libros = new ArrayList<>();
	}
	
	public void addLibro(Libro l) {
		if(!libros.contains(l)) {
			libros.add(l);			
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public ArrayList<Libro> getLibros() {
		return new ArrayList<>(libros);
	}
	
	
	
	
}
