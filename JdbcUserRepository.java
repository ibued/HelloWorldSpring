# HelloWorldSpring

package org.com.databarang.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;
import org.com.databarang.model.DatauserRowMapper;
import org.com.databarang.model.dataUser;
import org.com.databarang.repository.UserRepository;

/**
 *
 * @author ibued
 */

@Repository
public class JdbcUserRepository implements UserRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
	private SimpleJdbcInsert insertUser;

	@Autowired
	public jdbcUserRepository(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("datauser");
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public Collection<dataUser> getAll() throws DataAccessException {
		return this.namedParameterJdbcTemplate.query(
				"SELECT * FROM datauser ORDER BY iduser", 
				new DatauserRowMapper());		
	}

	@Override
	public void simpan(dataUser datauser) throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>(4);	
		params.put("iduser", datauser.getIduser());
		params.put("namauser", datauser.getNamauser());
		params.put("alamat", datauser.getAlamat());
		params.put("telp", datauser.getTelp());
		this.insertUser.execute(params);
	}

	@Override
	public void simpanUser(dataUser user) throws DataAccessException {
		//dataUser user;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("iduser", user.getIduser());
			map.put("namauser", user.getNamauser());
			map.put("alamat", user.getAlamat());
			map.put("telp", user.getTelp());

			this.namedParameterJdbcTemplate.update(
					"UPDATE datauser SET namauser=:namauser, alamat=:alamat, telp=:telp " +
							" WHERE iduser=:iduser",
							map);		
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectRetrievalFailureException(dataUser.class, user);
		}	
	}

	@Override
	public dataUser editUser(String id) throws DataAccessException {

		dataUser user;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("iduser", id);
			user = this.namedParameterJdbcTemplate.queryForObject(
					"SELECT iduser, namauser, alamat, telp FROM datauser WHERE iduser=:iduser",  
					map, ParameterizedBeanPropertyRowMapper.newInstance(dataUser.class));

		} catch (EmptyResultDataAccessException e) {
			throw new ObjectRetrievalFailureException(dataUser.class, id);
		}
		return user;
	}
	
	// MENUJU KE FORM
	@Override
	public dataUser hapus(String id) throws DataAccessException {
		return null;
	}

	// SIMPAN
	@Override
	public void hapusUser(String id) throws DataAccessException {		
		System.out.println("------------COBA HAPUS BUG--------------"+id);	

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("iduser", id);
			this.namedParameterJdbcTemplate.update(
					"DELETE FROM datauser WHERE iduser=:iduser",  
					map);
			//System.out.println("-----------FORM BUG---------------"+user.getNamaUser());		
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectRetrievalFailureException(dataUser.class, id);
		}
		
	}

}
