package ar.edu.uner.prestabook.persistence;

import java.util.List;

import ar.edu.uner.prestabook.common.GenericDAO;
import ar.edu.uner.prestabook.model.Prestamo;

public interface IPrestamoDAO extends GenericDAO<Prestamo> {

    public List<Prestamo> findAllByLectorId(Integer idLector);

}
