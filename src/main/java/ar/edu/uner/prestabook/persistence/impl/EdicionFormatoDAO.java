package ar.edu.uner.prestabook.persistence.impl;

import java.sql.Connection;

import java.util.List;

import ar.edu.uner.prestabook.persistence.IEdicionFormatoDAO;

public class EdicionFormatoDAO implements IEdicionFormatoDAO {

	Connection conn;

	public EdicionFormatoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

}
