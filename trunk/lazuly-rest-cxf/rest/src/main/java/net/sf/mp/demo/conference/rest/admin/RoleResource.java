package net.sf.mp.demo.conference.rest.admin;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

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
@Path ("/rest/xml/roles")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class RoleResource  {
 

	@Autowired
	@Qualifier("roleDao")
	RoleDao roleDao;
	
	@Autowired
	@Qualifier("roleExtDao")
	RoleExtDao roleExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-role@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
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
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Role findById (@PathParam ("id") java.lang.Integer id) {
        Role _role = new Role ();
		_role.setId(id);
		_role = roleExtDao.getFirstRole(_role);
		if (_role!=null) return _role.flat();
		return new Role ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Integer id) {
        Role role = new Role ();
        role.setId(id);
        roleDao.deleteRole(role);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Role create (
        @FormParam("id") Integer id,
        @FormParam("name") String name,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Role _role = new Role (
           id,
           name);
        return save(_role);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Role save(JAXBElement<Role> jaxbRole) {
        Role role = jaxbRole.getValue();
        if (role.getId()!=null)
            return roleDao.updateRole(role);
        return save(role);
    }

	public Role save (Role role) {
		roleDao.saveRole(role);
		return role;
	}


}