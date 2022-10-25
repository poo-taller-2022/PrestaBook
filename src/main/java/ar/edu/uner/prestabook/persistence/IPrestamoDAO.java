package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Prestamo;



/**
 * 
 * Interface defining the contract for the bussiness logic of Prestamo
 *
 */
public interface IPrestamoDAO extends GenericDAO<Prestamo> {

    /**
     * Finds all prestamos filtered by the reader dni
     * 
     *  @param documentoLector dni of lector in Prestamo
     * @return List of objects Prestamo
     */
    public List<Prestamo> findAllByLectorId(Long documentoLector);

	public Prestamo findByIdEjemplar(Long idEjemplar);
    /**
     * Counts how many times a book with the given isbn has been loaned
     * 
     * @param isbn of a book
     * @return the times the book has been loaned
     */
    public Long countByObraIsbn(String isbn);

    /**
     * Delete the prestamo according to the id given
     * 
     * @param idPrestamo id that refers to a prestamo
     * 
     */
    public void delete(Long idPrestamo);

}
