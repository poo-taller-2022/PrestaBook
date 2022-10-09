package ar.edu.uner.prestabook.persistence;

public interface IUsuarioDAO {

    public String buscarUsuarioRegistrado(String tipo, String correo, String contrasenia);

    public String buscarNombre(String tipo, String correo);
}
