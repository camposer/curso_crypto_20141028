package crypto.rmi.persona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import crypto.rmi.dao.PersonaDao;
import crypto.rmi.dao.PersonaDummyDaoImpl;
import crypto.rmi.to.Persona;

public class PersonaServantImpl 
		extends UnicastRemoteObject
		implements PersonaServant {

	private static final long serialVersionUID = -281030892851325706L;
	
	private PersonaDao personaDao;
	
	public PersonaServantImpl() throws RemoteException {
		super();
		this.personaDao = new PersonaDummyDaoImpl();
	}
	
	@Override
	public void agregarPersona(Persona p) throws RemoteException {
		personaDao.agregar(p);
	}

	@Override
	public void modificarPersona(Persona p) throws RemoteException {
		personaDao.modificar(p);
	}

	@Override
	public void eliminarPersona(Integer id) throws RemoteException {
		personaDao.eliminar(id);
	}

	@Override
	public Persona obtenerPersona(Integer id) throws RemoteException {
		return personaDao.obtener(id);
	}

	@Override
	public List<Persona> obtenerPersonas() throws RemoteException {
		return personaDao.obtenerTodos();
	}

}
