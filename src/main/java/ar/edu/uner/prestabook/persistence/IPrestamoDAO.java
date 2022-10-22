package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Prestamo;

public interface IPrestamoDAO extends GenericDAO<Prestamo> {

	
    
    /**
     * Finds all prestamos filtered by the reader dni
     * @return list of objects Prestamo
     * @param documentoLector dni of lector in Prestamo
     */
    public List<Prestamo> findAllByLectorId(Long documentoLector);

	public List<Prestamo> findAllByIdEjemplar(Long idEjemplar);

}
