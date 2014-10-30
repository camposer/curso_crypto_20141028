package crypto.rmi.dao;

import java.util.ArrayList;
import java.util.List;

import crypto.rmi.to.Persona;

public class PersonaDummyDaoImpl implements PersonaDummyDao {
	private List<Persona> personas = new ArrayList<Persona>();
	private int contador = 1;

	@Override
	public void agregar(Persona p) {
		p.setId(contador++);
		personas.add(p);
	}

	@Override
	public void modificar(Persona p) {
		Persona pOld = obtener(p.getId());
		if (pOld != null)
			personas.set(personas.indexOf(pOld), p);
	}

	@Override
	public void eliminar(Integer id) {
		Persona p = obtener(id);
		if (p != null)
			personas.remove(p);
	}

	@Override
	public Persona obtener(Integer id) {
		for (Persona p : personas)
			if (p.getId().equals(id))
				return p;
		
		return null;
	}

	@Override
	public List<Persona> obtenerTodos() {
		return personas;
	}

}
