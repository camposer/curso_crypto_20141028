package crypto.rmi.calculadora;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraServantImpl 
	extends UnicastRemoteObject
	implements CalculadoraServant {

	private static final long serialVersionUID = -6971418219879687151L;

	protected CalculadoraServantImpl() throws RemoteException {
		super();
	}

	@Override
	public float sumar(float a, float b) throws RemoteException {
		return a + b;
	}

	@Override
	public float restar(float a, float b) throws RemoteException {
		return a - b;
	}

	@Override
	public float multiplicar(float a, float b) throws RemoteException {
		return a * b;
	}

	@Override
	public float dividir(float a, float b) throws RemoteException {
		return a / b;
	}

}
