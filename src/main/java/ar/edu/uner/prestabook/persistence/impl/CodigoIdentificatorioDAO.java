package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ar.edu.uner.prestabook.connection.ConnectionProvider;
import ar.edu.uner.prestabook.model.CodigoIdentificatorio;
import ar.edu.uner.prestabook.persistence.ICodigoIdentificatorioDAO;

public class CodigoIdentificatorioDAO implements ICodigoIdentificatorioDAO{
	
	 /**
     * Singleton instance of the class
     */
    private static final CodigoIdentificatorioDAO instance = new CodigoIdentificatorioDAO();

    /**
     * Private constructor to avoid instantiation
     */
    private CodigoIdentificatorioDAO() {
    }

    /**
     * 
     * @return the singleton instance of the class
     */
    public static CodigoIdentificatorioDAO getInstance() {
        return instance;
    }

    @Override
    public List<CodigoIdentificatorio> findAll() {
        String sql = "SELECT * FROM CODIGOS_IDENTIFICATORIOS";
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            List<CodigoIdentificatorio> codigosIdentificatorios = new LinkedList<>();
            while (resultados.next()) {
                codigosIdentificatorios.add(toCodigoIdentificatorio(resultados));
            }
            return codigosIdentificatorios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    @Override
    public CodigoIdentificatorio findById(Object codigo) {
        String sql = String.format("SELECT * FROM CODIGOS_IDENTIFICATORIOS WHERE CODIGO = %s", codigo.toString());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultados = statement.executeQuery();
            CodigoIdentificatorio codigoIden = null;
            if (resultados.next()) {
                codigoIden = toCodigoIdentificatorio(resultados);
            }
            return codigoIden;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer insert(CodigoIdentificatorio codigoIdentificatorio) {
        String sql = String.format("INSERT INTO CODIGOS_IDENTIFICATORIOS (PASILLO,ESTANTERIA,ESTANTE) VALUES ('%s','%s','%s')",
        		codigoIdentificatorio.getPasillo(),codigoIdentificatorio.getEstanteria(),codigoIdentificatorio.getEstante());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(CodigoIdentificatorio codigoIdentificatorio) {
        String sql = String.format("UPDATE CODIGOS_IDENTIFICATORIOS SET CODIGO = '%s', PASILLO = '%s', ESTANTERIA = '%s', ESTANTE = '%s'",
        		codigoIdentificatorio.getCodigo(),codigoIdentificatorio.getPasillo(),codigoIdentificatorio.getEstanteria(),codigoIdentificatorio.getEstante());
        try (Connection conn = ConnectionProvider.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(CodigoIdentificatorio cod) {
        return null;
    }


    
    /**
     * Converts a ResultSet into a CodigoIdentificatorio
     * 
     * @param resultados the ResultSet with the rows obtained from the database
     *                   query
     * @return a CodigoIdentificatorio
     */
	private CodigoIdentificatorio toCodigoIdentificatorio(ResultSet resultados) {
		   CodigoIdentificatorio codigo = new CodigoIdentificatorio();
	        try {
	            codigo.setCodigo(resultados.getLong(1));
	            codigo.setEstante(resultados.getInt(2));
	            codigo.setEstanteria(resultados.getInt(3));
	            codigo.setPasillo(resultados.getInt(4));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return codigo;
	}

	
}
