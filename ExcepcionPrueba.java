package gestionPersonasAplicacion;

@SuppressWarnings("serial")

public class ExcepcionPrueba extends RuntimeException {

	public ExcepcionPrueba(String mensage) {
		super("Fallo: " + mensage);
	}
}
