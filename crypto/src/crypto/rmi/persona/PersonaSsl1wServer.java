package crypto.rmi.persona;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PersonaSsl1wServer {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("personaServant", new PersonaServantSsl1wImpl());
		System.out.println("Registro rmi iniciado...");
	}
}
