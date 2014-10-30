package crypto.rmi.dao;

import java.util.List;

import crypto.rmi.to.Persona;

//CRUD+ = Create / Read / Update / Delete / List
public interface PersonaDummyDao {
	void agregar(Persona p);
	void modificar(Persona p);
	void eliminar(Integer id);
	Persona obtener(Integer id);
	List<Persona> obtenerTodos();
}