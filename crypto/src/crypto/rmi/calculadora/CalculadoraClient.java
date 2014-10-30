package crypto.rmi.calculadora;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraClient {
	private static final String HOST = "localhost";
	private static final int PORT = 1099;
	
	public static void main(String[] args) throws Exception {
		// Obteniendo referencia al rmi registry
		Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		
		// Buscando objeto remoto
		Remote obj = registry.lookup("calculadoraServant");
		if (obj instanceof CalculadoraServant) {
			CalculadoraServant calculadoraServant = 
					(CalculadoraServant)obj;
			
			System.out.println("2 + 2 = " + calculadoraServant.sumar(2, 3));
		}
	}
}
