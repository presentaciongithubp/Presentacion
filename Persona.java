package gestionPersonasAplicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Clase Persona
 *
 * Contiene información de una persona
 */
public class Persona {
	private String dni;
	private String nombre;
	private double altura;
	private Sexo sexo;
	private Date fechaNac;

	public Persona(String dni) {
		setDni(dni);
	}

	public static String validarDNI(String dni) {
		// Devuelve el dni con 0's a la izquierda(hasta 8) si el dni es válido.
		// null si el dni no es válido
		char letras[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		dni = dni.trim().toUpperCase(); // Quita espacios en blanco y convierte a mayúsculas
		if (!dni.matches("[0-9]{1,8}[A-Z]"))
			return null;
		// Rellenamos con ceros por la izquierda
		while (dni.length() < 9)
			dni = "0" + dni;
		String digitos = dni.substring(0, dni.length() - 1);
		char letra = dni.charAt(dni.length() - 1);// La letra es el ultimo caracter de dni

		if (letra != letras[Integer.valueOf(digitos) % 23])
			return null;
		return dni;
	}

	public int obtenerEdad() throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("MM");
		SimpleDateFormat f3 = new SimpleDateFormat("dd");

		Date d = new Date();
		int anio = Integer.valueOf(f.format(d)) - Integer.valueOf(f.format(fechaNac));

		if (Integer.valueOf(f2.format(d)) - Integer.valueOf(f2.format(fechaNac)) > 0)
			return anio;
		else if (Integer.valueOf(f2.format(d)) - Integer.valueOf(f2.format(fechaNac)) < 0)
			return anio - 1;
		else if (Integer.valueOf(f3.format(d)) - Integer.valueOf(f3.format(fechaNac)) >= 0)
			return anio;
		else
			return anio - 1;
	}
	
		public static String generarDni() {
		char letras[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		Random r = new Random();
		int num = r.nextInt(99999999);

		String dni = String.valueOf(num);

		while (dni.length() < 8)
			dni = "0" + dni;

		dni = dni + (letras[num % 23]);

		return dni;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		String norm = validarDNI(dni);
		if (norm == null)
			throw new ExcepcionDNI("DNI incorrecto " + dni);
		this.dni = norm;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		nombre = nombre.trim();
		if (nombre.length() == 0)
			throw new ExcepcionNombre("El nombre no puede estar vacío");
		this.nombre = nombre;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		if (altura <= 0 || altura >= 3)
			throw new ExcepcionAltura("La altura(metros) debe ser mayor que 0 y menor que 3");
		this.altura = altura;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setSexo(String s) {
		s = s.toLowerCase();

		switch (s) {
		case "h":
		case "hombre":
			this.sexo = Sexo.Hombre;
			break;
		case "m":
		case "mujer":
			this.sexo = Sexo.Mujer;
			break;
		case "o":
		case "otro":
			this.sexo = Sexo.Otro;
			break;
		default:
			throw new ExcepcionSexo("El valor introducido no es válido ('H', 'M', 'O', 'Hombre', 'Mujer', 'Otro')");
		}
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public String mostrarFechaNac() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(fechaNac);
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setFechaNac(String fecha) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		f.setLenient(false);

		try {
			this.fechaNac = f.parse(fecha);
		} catch (ParseException e) {
			throw new ExcepcionFechaNacimiento("La fecha no es válida para el formato dd/MM/yyyy");
		}
	}
	public int metodo2(){
		return 0;
		}

	@Override
	public String toString() {
		try {
			return "[dni=" + dni + ", nombre=" + nombre + ", altura=" + altura + ", s=" + sexo + ", fecha nacimiento="
					+ mostrarFechaNac() + " (" + obtenerEdad() + " años)" + "]";
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	

}
