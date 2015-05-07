# HelloWorldSpring

package org.com.databarang.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ibued
 */

public class DatauserRowMapper implements RowMapper<DataUser> {

	@Override
	public DataUser mapRow(ResultSet rs, int rownumber) throws SQLException {
		DataUser obj = new DataUser();
		obj.setIduser(rs.getString("iduser"));
		obj.setNamauser(rs.getString("namauser"));
		obj.setAlamat(rs.getString("alamat"));
		obj.setTelp(rs.getString("telp"));
		return obj;
	}

}
