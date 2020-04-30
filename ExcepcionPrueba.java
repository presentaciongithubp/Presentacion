package gestionPersonasAplicacion;

@SuppressWarnings("serial")

public class ExcepcionPrueba extends RuntimeException{

	public ExcepcionPrueba(String mensage) {
			super("Error 404: "+mensage);
	}
	
}
