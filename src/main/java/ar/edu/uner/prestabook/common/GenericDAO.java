package ar.edu.uner.prestabook.common;

import java.util.List;

/**
 * Generic interface for bussiness logic
 *
 * @param <T> the entity that the DAO will handle
 */
public interface GenericDAO<T> {

    /**
     * Finds all the entities in the database
     * 
     * @return a list of entitiesa
     */
    public List<T> findAll();

    /**
     * Finds an entity by its id
     * 
     * @param id The id to search for
     * @return an entity if the id matches the database field, null if it does not
     */
    public T findById(Object id);

    /**
     * Inserts an entity to the database
     * 
     * @param t The entity to insert to the database
     * @return either the row count for SQL Data Manipulation Language (DML)
     *         statements or null for SQL statements that return nothing
     */
    public T insert(T t);

    /**
     * Updates an entity of the database
     * 
     * @param t The entity to update
     * @return either the row count for SQL Data Manipulation Language (DML)
     *         statements or null for SQL statements that return nothing
     */
    public T update(T t);

}
