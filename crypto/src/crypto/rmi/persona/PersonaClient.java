package crypto.rmi.persona;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import crypto.rmi.to.Persona;

public class PersonaClient {
	private static final int OP_AGREGAR = 0;
	private static final int OP_MODIFICAR = 1;
	private Scanner scanner;
	private PersonaServant personaServant;
	
	public static void main(String[] args) throws Exception {
		new PersonaClient().iniciar();
	}
	
	public PersonaClient() throws Exception {
		this.scanner = new Scanner(System.in);
		Registry registry = LocateRegistry.getRegistry();
		this.personaServant = 
				(PersonaServant)registry.lookup("personaServant");
	}
	
	public void iniciar() throws Exception {
		while (true) {
			System.out.println();
			System.out.println("Gestión de personas");
			System.out.println("1. Agregar");
			System.out.println("2. Modificar");
			System.out.println("3. Eliminar");
			System.out.println("4. Obtener uno");
			System.out.println("5. Obtener todos");
			System.out.println("6. Salir");
			System.out.print("? ");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("1"))
				guardar(OP_AGREGAR);
			else if (opcion.equals("2"))
				guardar(OP_MODIFICAR);
			else if (opcion.equals("3"))
				eliminar();
			else if (opcion.equals("4"))
				obtener();
			else if (opcion.equals("5"))
				obtenerTodos();
			else if (opcion.equals("6"))
				break;
		}
	}

	private void obtener() throws Exception {
		Integer id = null;
		
		System.out.print("Id? ");
		String sid = scanner.nextLine();
		id = Integer.parseInt(sid);
		
		Persona p = personaServant.obtenerPersona(id);
		System.out.println(p);
	}

	private void eliminar() throws Exception {
		Integer id = null;
		
		System.out.print("Id? ");
		String sid = scanner.nextLine();
		id = Integer.parseInt(sid);
		
		personaServant.eliminarPersona(id);
	}

	private void guardar(int op) throws Exception {
		Integer id = null;
		
		if (op == OP_MODIFICAR) {
			System.out.print("Id? ");
			String sid = scanner.nextLine();
			id = Integer.parseInt(sid);
		}
		System.out.print("Nombre? ");
		String nombre = scanner.nextLine();
		System.out.print("Apellido? ");
		String apellido = scanner.nextLine();
		System.out.print("Fecha de Nacimiento? ");
		String sfnac = scanner.nextLine();
		Date fnac = null;
		try {
			fnac = new SimpleDateFormat("yyyy-MM-dd").parse(sfnac);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Persona p = new Persona(nombre, apellido, fnac);
		
		if (op == OP_MODIFICAR) {
			p.setId(id);
			personaServant.modificarPersona(p);
		} else {
			personaServant.agregarPersona(p);
		}
		
	}
	
	private void obtenerTodos() throws Exception {
		for (Persona p : personaServant.obtenerPersonas())
			System.out.println(p);
		
	}

	
}
