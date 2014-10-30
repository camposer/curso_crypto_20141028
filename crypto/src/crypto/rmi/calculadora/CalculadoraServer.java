package crypto.rmi.calculadora;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraServer {
	 public static void main(String[] args) throws Exception {
		// Instanciando el registro rmi
		Registry registry = LocateRegistry.createRegistry(1099);
		
		// Creando objeto remoto
		CalculadoraServant calculadoraServant = 
				new CalculadoraServantImpl();
		
		// Enlzando objeto en el registro
		registry.rebind("calculadoraServant", calculadoraServant);
		System.out.println("Registro creado y objetos enlazados...");
	}
}
