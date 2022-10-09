package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.Funcionario;
import ar.edu.uner.prestabook.persistence.IFuncionarioDAO;

public class FuncionarioDAO implements IFuncionarioDAO {

    /**
     * Singleton instance of the class
     */
    private static final FuncionarioDAO instance = new FuncionarioDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private FuncionarioDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static FuncionarioDAO getInstance() {
        return instance;
    }

    @Override
    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM FUNCIONARIOS";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<Funcionario> funcionario = new LinkedList<>();
            while (resultados.next()) {
                funcionario.add(toFuncionario(resultados));
            }
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Funcionario findById(Object id) {

        String sql = String.format("SELECT * FROM FUNCIONARIOS WHERE ID = %s", id.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            Funcionario funcionario = null;
            if (resultados.next()) {
                funcionario = toFuncionario(resultados);
            }
            return funcionario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(Funcionario funcionario) {
        String sql = String.format(
                "INSERT INTO FUNCIONARIOS (NOMBRE, APELLIDO, TIPO_DOCUMENTO, DNI,"
                        + "EMAIL, CELULAR, FECHA_NACIMIENTO, SEXO, NACIONALIDAD, DOMICILIO,"
                        + "CODIGO_POSTAL, DEPARTAMENTO, LOCALIDAD, CONTRASENIA) VALUES ('%s','%s','%s','%s','%s','%s',"
                        + "'%s','%s','%s','%s','%s','%s','%s','%s')",
                funcionario.getNombre(), funcionario.getApellido(), funcionario.getTipoDocumento(),
                funcionario.getDocumento(), funcionario.getEmail(), funcionario.getCelular(),
                funcionario.getFechaNacimiento(), funcionario.getSexo(), funcionario.getNacionalidad(),
                funcionario.getDomicilio(), funcionario.getCodigoPostal(), funcionario.getDepartamento(),
                funcionario.getLocalidad(), funcionario.getContrasenia());

        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(Funcionario funcionario) {

        String sql = String.format(
                "UPDATE FUNCIONARIOS SET NOMBRE = '%s', APELLIDO = '%s', TIPO_DOCUMENTO = '%s', DNI = '%s', "
                        + "EMAIL = '%s', CELULAR = '%s', FECHA_NACIMIENTO = '%s', SEXO = '%s', NACIONALIDAD = '%s', DOMICILIO = '%s', "
                        + "CODIGO_POSTAL = '%s', DEPARTAMENTO = '%s', LOCALIDAD = '%s' " + "WHERE ID = '%s'",
                funcionario.getNombre(), funcionario.getApellido(), funcionario.getTipoDocumento(),
                funcionario.getDocumento(), funcionario.getEmail(), funcionario.getCelular(),
                funcionario.getFechaNacimiento(), funcionario.getSexo(), funcionario.getNacionalidad(),
                funcionario.getDomicilio(), funcionario.getCodigoPostal(), funcionario.getDepartamento(),
                funcionario.getLocalidad(), funcionario.getId());

        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Funcionario funcionario) {
        return 0;
    }

    /**
     * Converts a ResultSet into a Funcionario
     * 
     * @param resultados the ResultSet with the rows obtained from the database
     *                   query
     * @return a Funcionario
     */
    private Funcionario toFuncionario(ResultSet resultados) {
        Funcionario funcionario = new Funcionario();
        try {
            funcionario.setId(resultados.getLong(1));
            funcionario.setNombre(resultados.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

}
