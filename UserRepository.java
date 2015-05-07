# HelloWorldSpring
package org.com.databarang.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.com.databarang.model.dataUser;

/**
 *
 * @author ibued
 */

public interface UserRepository {

  Collection<dataUser> getAll() throws DataAccessException;
    
  dataUser editUser(String id) throws DataAccessException;

	void simpan(dataUser dataUser) throws DataAccessException;
	
	public void simpanUser(dataUser user) throws DataAccessException;
	
	public dataUser hapus(String id) throws DataAccessException;
	
	public void hapusUser(String id) throws DataAccessException;
  
}
