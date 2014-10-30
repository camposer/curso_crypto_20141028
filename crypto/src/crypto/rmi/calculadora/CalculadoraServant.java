package crypto.rmi.calculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraServant extends Remote {
	float sumar(float a, float b) throws RemoteException;
	float restar(float a, float b) throws RemoteException;
	float multiplicar(float a, float b) throws RemoteException;
	float dividir(float a, float b) throws RemoteException;
}
