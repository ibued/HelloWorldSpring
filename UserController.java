# HelloWorldSpring

package org.com.databarang.web;

import java.util.Collection;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.com.databarang.model.dataUser;
import org.com.databarang.service.dataUserService;

/**
 *
 * @author ibued
 */

@Controller
public class UserController {

	private final dataUserService userService;

	@Autowired
	public UserController(dataUserService clinicService) {
		this.userService = clinicService;
	}	  

	@RequestMapping(value="/datauser", method = RequestMethod.GET)
	public String processFindForm(dataUser user, BindingResult result, Map<String, Object> model) {
		Collection<dataUser> results = this.userService.getAll();
		model.put("datauser", results);
		return "datauser";        
	}    	

	@RequestMapping(value="/masukandatauser", method = RequestMethod.POST)
	public String datauser(@Valid @ModelAttribute("datauser") dataUser dataUser, BindingResult result, SessionStatus status,  Map<String, Object> model) {

		if (result.hasErrors()) {				
			//model.put("datauser", new dataUser());
			return "masukandatauser";
		}
		this.userService.simpan(dataUser);
		status.setComplete();
		JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah");
		return "redirect:/datauser";			
	}

	@RequestMapping(value="/masukandatauser", method = RequestMethod.GET)
	public String tambahUser(Map<String, Object> model) {
		dataUser user = new dataUser();
		// m.addAttribute(new dataUser());
		model.put("datauser", user);
		return "masukandatauser";        
	}

	@RequestMapping(value="/editFormUser/{iduser}", method = RequestMethod.GET)
	public String editUser(@PathVariable("iduser")String idUser, Map<String, Object> model) {
		// m.addAttribute(new dataUser());
		dataUser user = this.userService.formEditUser(idUser);
		model.put("datauser", user);
		return "editFormUser";        
	}

	@RequestMapping(value="/editFormUser/{iduser}", method = RequestMethod.PUT)
	public String updateUser(@Valid dataUser dataUser, BindingResult result, SessionStatus status) {
		// m.addAttribute(new dataUser());
		this.userService.update(dataUser);
		status.setComplete();
		//dataUser user = this.userService.formEditUser(idUser);
		//model.put("datauser", user);
		return "redirect:/datauser";        
	}

	@RequestMapping(value="/datauser/{iduser}", method = RequestMethod.GET)
	public String formHapusUser(@PathVariable("iduser") String idUser, SessionStatus status, Map<String, Object> model) {
		int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID "+idUser+" ?", "Question",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (konfirm == JOptionPane.YES_NO_OPTION) {
			userService.hapusUser(idUser);
			model.put("datauser", idUser);
			status.setComplete();
			return "redirect:/datauser"; 
		}
		return "redirect:/datauser";	       
	}	

}

