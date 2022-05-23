import java.util.ArrayList;

public class Biblioteca {
	ArrayList<Genero> casilleros;
	
	
	
	
	public void addLibro(Libro l) {
		Boolean flag = false;
		ArrayList<String> generosDeLibro = l.getGeneros();
		for(String g:generosDeLibro) {
			for(Genero j:casilleros) {
				if(j.getNombre().equals(g)) {
					j.addLibro(l);
					flag = true;
				}
			}
			if(!flag) {
				Genero x = new Genero(g, casilleros.size());
				this.addCasillero(x);
				x.addLibro(l);
			}
		}
	}
	
	public void addCasillero(Genero g) {
		casilleros.add(g);
	}
	
	public ArrayList<Libro> getLibrosPorGenero(Genero g){
		ArrayList<Libro> librosDeGenero = new ArrayList<>();
		
		return librosDeGenero;
	}
}
