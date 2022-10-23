package ar.edu.uner.prestabook.persistence;

import ar.edu.uner.prestabook.exception.UserNotFoundException;
import ar.edu.uner.prestabook.exception.WrongPasswordException;

public interface IUsuarioDAO {
	

    /**
     * Searchs for a registered user in the database
     * 
     * @param tipo        refers to the differents type user
     * @param Email       of user
     * @param contrasenia of user
     * @return true if the user is found
     */
    public Boolean buscarUsuarioRegistrado(String tipo, String correo, String contrasenia)
            throws WrongPasswordException, UserNotFoundException;

    /**
     * Get the full name of the user
     * 
     * @param tipo   Refers to the differents type user
     * @param correo Email of user
     * @return String with name and surname
     */
    public String buscarNombre(String tipo, String correo);   
}