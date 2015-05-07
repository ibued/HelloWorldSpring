# HelloWorldSpring

package org.com.databarang.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;


import org.com.databarang.model.dataUser;

public interface DataUserService {

	public Collection<dataUser> getAll() throws DataAccessException;   
	public dataUser formEditUser(String id) throws DataAccessException;
	public dataUser hapus (String id) throws DataAccessException;
	
	public void simpan(dataUser dataUser) throws DataAccessException;
	public void update(dataUser dataUser) throws DataAccessException;
	public void hapusUser(String id) throws DataAccessException;
}
