# HelloWorldSpring

package org.com.databarang.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ibued
 */

public class DataUser {
	
	@NotEmpty
	private String iduser;

	@NotEmpty
	private String namauser;	

	@NotEmpty
	private String alamat;
	
	@NotEmpty
	private String telp;

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getNamauser() {
		return namauser;
	}

	public void setNamauser(String namauser) {
		this.namauser = namauser;
	}	
	
	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}
   
	public boolean isNew() {
		return (this.iduser == null);
	}
	
}
