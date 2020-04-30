package gestionPersonasAplicacion;

import java.text.ParseException;
import java.util.ArrayList;

import gestionPersonasAplicacion.ExcepcionAltura;
import gestionPersonasAplicacion.ExcepcionDNI;
import gestionPersonasAplicacion.ExcepcionNombre;
import gestionPersonasAplicacion.Persona;
import utilidades.Entrada;

public class GestionPersonas {

	static ArrayList<Persona> al = new ArrayList<Persona>();

	public static void menu() {
		System.out.println("+---------------------------------+");
		System.out.println("|                                 |");
		System.out.println("| Seleccione una opción:          |");
		System.out.println("|                                 |");
		System.out.println("| 1- Altas                        |");
		System.out.println("| 2- Bajas                        |");
		System.out.println("| 3- Modificaciones               |");
		System.out.println("| 4- Consultas                    |");
		System.out.println("| 5- Listado                      |");
		System.out.println("|                                 |");
		System.out.println("| 0- Salir                        |");
		System.out.print("|                 Personas: ");
		System.out.format("%5s", al.size());
		System.out.println(" |");
		System.out.println("+---------------------------------+");
	}

	public static int buscarDni(String dni) {
		String dni2 = gestionPersonasAplicacion.Persona.validarDNI(dni);
		for (int i = 0; i < al.size(); i++)
			if (al.get(i).getDni().equalsIgnoreCase(dni2)) {
				return i;
			}
		return -1;
	}

	public static void alta() {
		Persona p = null;
		boolean bien = true;

		System.out.print("DNI?");
		String dni = Entrada.cadena();

		if (dni.equalsIgnoreCase("aleatorio") || dni.equalsIgnoreCase("random") || dni.equalsIgnoreCase("r"))
			dni = Persona.generarDni();

		if (buscarDni(dni) == -1) {
			try {
				p = new Persona(dni);

				do {
					bien = true;
					try {
						System.out.print("Nombre?");
						String nombre = Entrada.cadena();
						p.setNombre(nombre);
					} catch (ExcepcionNombre e) {
						System.out.println(e.getMessage());
						bien = false;
					}
				} while (!bien);

				do {
					bien = true;
					try {
						System.out.print("Altura?");
						double altura = Entrada.real();
						p.setAltura(altura);
					} catch (ExcepcionAltura e) {
						System.out.println(e.getMessage());
						bien = false;
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
						bien = false;
					}
				} while (!bien);

				do {
					bien = true;
					try {
						System.out.print("Sexo? (Hombre, Mujer u Otro)");
						p.setSexo(Entrada.cadena());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						bien = false;
					}

				} while (!bien);

				do {
					bien = true;
					try {
						System.out.println("Fecha de nacimiento? (DD/MM/YYYY)");
						p.setFechaNac(Entrada.cadena());
					} catch (ExcepcionFechaNacimiento e) {
						System.out.println(e.getMessage());
						bien = false;
					}
				} while (!bien);

				al.add(p);

			} catch (ExcepcionDNI e) {
				System.out.println(e.getMessage());
				bien = false;
			}

		} else
			System.out.println("Ya hay alguien con ese dni.");
	}

	public static void baja() {
		System.out.println("DNI?");
		String dni = Entrada.cadena();
		int d = buscarDni(dni);

		if (d == -1)
			System.out.println("No hay nadie con ese DNI.");
		else {
			al.remove(d);
			System.out.println("Persona eliminada.");
		}
	}

	public static void mod() {
		System.out.println("DNI?");
		String dni = Entrada.cadena();
		int d = buscarDni(dni);
		boolean valido = true;

		if (d == -1)
			System.out.println("No hay nadie con ese dni.");
		else {
			System.out.println(al.get(d).toString());

			System.out.println("Nombre?" + " [" + al.get(d).getNombre() + "]");
			String nombre = Entrada.cadena();

			if (!(nombre.equals("")))
				al.get(d).setNombre(nombre);

			do {
				valido = true;
				System.out.println("Altura?" + " [" + al.get(d).getAltura() + "]");
				String altura = Entrada.cadena();

				if (!(altura.equals(""))) {
					try {
						al.get(d).setAltura(Double.valueOf(altura));
					} catch (Exception e) {
						System.out.println("Altura no válida");
						valido = false;
					}
				}
			} while (!valido);

			do {
				valido = true;
				System.out.println("Sexo?" + " [" + al.get(d).getSexo() + "]");
				String sexo = Entrada.cadena();

				if (!(sexo.equals("")))
					try {
						al.get(d).setSexo(sexo);
					} catch (Exception e) {
						System.out.println("Sexo no válido");
						valido = false;
					}
			} while (!valido);

			do {
				valido = true;
				try {
					System.out.println("Fecha de nacimiento?" + " [" + al.get(d).mostrarFechaNac() + " ("
							+ al.get(d).obtenerEdad() + " años)" + "]");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				String fecha = Entrada.cadena();

				if (!(fecha.equals("")))
					try {
						al.get(d).setFechaNac(fecha);
					} catch (ExcepcionFechaNacimiento e) {
						System.out.println("Fecha no válida");
						valido = false;
					}
			} while (!valido);

		}
	}

	public static void consul() {
		System.out.println("DNI?");
		String dni = Entrada.cadena();
		int d = buscarDni(dni);

		if (d == -1)
			System.out.println("No hay nadie con ese dni.");
		else
			System.out.println(al.get(d).toString());
	}

	public static void listar() throws InterruptedException {
		for (int i = 0; i < al.size(); i++)
			System.out.println(al.get(i).toString());
		Thread.sleep(500);
	}

	public static void main(String[] args) throws InterruptedException {
		boolean salir = false;
		boolean valido = false;
		int input = 0;

		do {
			do {
				try {
					menu();
					input = Entrada.entero();
					valido = true;
				} catch (Exception e) {
					System.out.println("Entrada incorrecta.");
					Thread.sleep(1000);
					valido = false;
				}
			} while (!valido);

			switch (input) {
			case 0:
				salir = true;
				break;
			case 1:
				alta();
				break;
			case 2:
				baja();
				break;
			case 3:
				mod();
				break;
			case 4:
				consul();
				break;
			case 5:
				listar();
				break;
			default:
				System.out.println("Entrada incorrecta.");
				break;
			}
			System.out.println("\n");
			Thread.sleep(500);
		} while (!salir);

		System.out.println("Adios!");
	}

}
