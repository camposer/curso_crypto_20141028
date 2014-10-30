package crypto.rmi.persona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import crypto.rmi.dao.PersonaDao;
import crypto.rmi.dao.PersonaDummyDaoImpl;
import crypto.rmi.to.Persona;

public class PersonaServantImpl 
		extends UnicastRemoteObject
		implements PersonaServant {

	private static final long serialVersionUID = -281030892851325706L;
	
	public static enum ModoSsl {
		SSL1W(true), SSL2W(false);
		
		private boolean validatedAtClient;
		
		private ModoSsl(boolean validatedAtClient) {
			this.validatedAtClient = validatedAtClient;
		}

		public boolean isValidatedAtClient() {
			return validatedAtClient;
		}
	}
	
	private PersonaDao personaDao;
	
	public PersonaServantImpl() throws RemoteException {
		super();
		init();
	}
	
	public PersonaServantImpl(ModoSsl modo) throws RemoteException {
		super(0, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, modo.isValidatedAtClient()));
		init();
	}
	
	private void init() {
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
