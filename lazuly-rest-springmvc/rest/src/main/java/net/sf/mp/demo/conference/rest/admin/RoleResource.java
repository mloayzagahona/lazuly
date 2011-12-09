package net.sf.mp.demo.conference.rest.admin;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


import net.sf.mp.demo.conference.dao.face.admin.RoleDao;
import net.sf.mp.demo.conference.dao.face.admin.RoleExtDao;
import net.sf.mp.demo.conference.domain.admin.Role;

/**
 *
 * <p>Title: RoleResource</p>
 *
 * <p>Description: remote interface for RoleResource service </p>
 *
 */
@RequestMapping ("/rest/xml/roles")
@Controller 
public class RoleResource  {

	@Autowired
	@Qualifier("roleDao")
	RoleDao roleDao;
	
	@Autowired
	@Qualifier("roleExtDao")
	RoleExtDao roleExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-role@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
    public List<Role> findAll () {
		List<Role> r = new ArrayList<Role>();
        List<Role> l = roleDao.searchPrototypeRole(new Role());
		for (Role role : l) {
			r.add(role.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-role@
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Role findById (@PathVariable ("id") java.lang.Integer id) {
        Role _role = new Role ();
		_role.setId(id);
		_role = roleExtDao.getFirstRole(_role);
		if (_role!=null) return _role.flat();
		return new Role ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Role create (
        @RequestParam("id") Integer id,
        @RequestParam("name") String name,
        HttpServletResponse servletResponse
        ) throws IOException {
        Role _role = new Role (
           id,
           name);
        return save(_role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Integer id) {
        Role role = new Role ();
        role.setId(id);
        roleDao.deleteRole(role);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Role save (
        @RequestParam("id") Integer id,
        @RequestParam("name") String name,
        HttpServletResponse servletResponse
        ) throws IOException {
        Role _role = new Role (
           id,
           name);
        return save(_role);
    }	


	public Role save (Role role) {
		roleDao.saveRole(role);
		return role;
	}



}