package crypto.rmi.persona;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import crypto.rmi.persona.PersonaServantImpl.ModoSsl;

public class PersonaSsl1wServer {
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind("personaServant", new PersonaServantImpl(ModoSsl.SSL1W));
		System.out.println("Registro rmi iniciado...");
	}
}
