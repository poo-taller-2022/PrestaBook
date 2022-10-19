package ar.edu.uner.prestabook.persistence;

public interface IUsuarioDAO {

	
    /**
     * Allows the user access to application
     * @param tipo , Refers to the differents type user
     * @param correo , Email of user
     * @param contrasenia of user
     * @return String , Confirming that the user is registered or not
     */
    public String buscarUsuarioRegistrado(String tipo, String correo, String contrasenia);

    
    /**
     * Get the username to display in the GUI
     * @param tipo Refers to the differents type user
     * @param correo Email of user
     * @return String with name and surname
     */
    public String buscarNombre(String tipo, String correo);
}
