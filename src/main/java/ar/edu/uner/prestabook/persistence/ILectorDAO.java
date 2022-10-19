package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Lector;

public interface ILectorDAO extends GenericDAO<Lector> {

	 
    /**
     * Count how many fines has a reader.
     * @return Long , number of fines the reader has
     * @param documento of reader
     */
    public Long countFinesById(Long documento);

}