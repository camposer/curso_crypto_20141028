package crypto.rmi.persona;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PersonaServer {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("personaServant", new PersonaServantImpl());
		System.out.println("Registro rmi iniciado...");
	}
}
