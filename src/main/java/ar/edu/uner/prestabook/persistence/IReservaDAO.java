package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Reserva;

public interface IReservaDAO extends GenericDAO<Reserva> {

    /**
     * Counts how many times a book with the given isbn is reserved at the time
     * 
     * @param isbn of a book
     * @return the ammount of reserves for this book
     */
    public Long countByObraIsbn(String isbn);

    
    /**
     * Finds a Reserva by the id of an ejemplar
     * 
     * @param idEjemplar Long that represents the id of a Ejemplar
     * @return entity of type Reserva if the idEjemplar matches the database field, null if it does not
     */
    public Reserva findByIdEjemplar(Long idEjemplar);

}
