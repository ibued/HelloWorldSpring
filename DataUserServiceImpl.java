# HelloWorldSpring

package org.com.databarang.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.com.databarang.model.dataUser;
import org.com.databarang.repository.UserRepository;

@Service
public class DataUserServiceImpl implements dataUserService {

	private UserRepository userRepo;
	// private dataUser dataUser;
	
	@Autowired
	public dataUserServiceImpl(UserRepository repository) {
		this.userRepo = repository;
	}
	
	@Override
	public Collection<dataUser> getAll() throws DataAccessException {
		return this.userRepo.getAll();
	}

	/*@Override
	public void simpan(org.unirow.databarang.model.dataUser dataUser)
			throws DataAccessException {
		this.userRepo.simpan(dataUser);		
	}*/
	
	@Override
	@Transactional
	public void simpan(dataUser dataUser) throws DataAccessException {
		userRepo.simpan(dataUser);
	}

	@Override
	@Transactional(readOnly = true)
	public dataUser formEditUser(String id) throws DataAccessException {
		return userRepo.editUser(id);
	}

	@Override
	public void update(dataUser dataUser) throws DataAccessException {
		userRepo.simpanUser(dataUser);
	}

	@Override
	@Transactional(readOnly = true)
	public dataUser hapus(String id) throws DataAccessException {
		return userRepo.hapus(id);
	}

	@Override
	public void hapusUser(String id) throws DataAccessException {
		userRepo.hapusUser(id);
	}

	/*@Override
	@Transactional(readOnly = true)
	public void hapus(dataUser dataUser, String id) throws DataAccessException {
		userRepo.hapus(id);
	}*/
	
	 /*@Override
	    @Transactional(readOnly = true)
	    public Owner findOwnerById(int id) throws DataAccessException {
	        return ownerRepository.findById(id);
	    }
	
	/*@Override
    @Transactional
    public void savePet(Pet pet) throws DataAccessException {
        petRepository.save(pet);
    }
*/

}
