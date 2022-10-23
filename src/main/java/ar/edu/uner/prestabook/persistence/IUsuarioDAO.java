package ar.edu.uner.prestabook.persistence;

public interface IUsuarioDAO {
	
    /**
     * Searchs for a registered user in the database
     * @param tipo refers to the differents type user
     * @param Email of user
     * @param contrasenia of user
     * @return A string confirming that the user is registered or not
     */
    public String buscarUsuarioRegistrado(String tipo, String correo, String contrasenia);

    
    /**
     * Get the full name of the user
     * @param tipo Refers to the differents type user
     * @param correo Email of user
     * @return String with name and surname
     */
    public String buscarNombre(String tipo, String correo);   
}