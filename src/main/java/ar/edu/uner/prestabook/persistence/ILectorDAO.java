package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Lector;

public interface ILectorDAO extends GenericDAO<Lector> {

    /**
     * Count how many fines a reader has.
     * @param documento of reader
     * @return The number of fines the reader has
     */
    public Long countFinesById(Long documento);

    /**
     * Finds a reader by their e-mail
     * 
     * @param email String that represents the email of the reader
     * @return  entity of type Lector
     */
    public Lector findByEmail(String email);

}