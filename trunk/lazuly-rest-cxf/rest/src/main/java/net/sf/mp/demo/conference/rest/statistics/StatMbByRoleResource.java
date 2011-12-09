package net.sf.mp.demo.conference.rest.statistics;


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

import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleDao;
import net.sf.mp.demo.conference.dao.face.statistics.StatMbByRoleExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbByRole;

/**
 *
 * <p>Title: StatMbByRoleResource</p>
 *
 * <p>Description: remote interface for StatMbByRoleResource service </p>
 *
 */
@Path ("/rest/xml/statmbbyroles")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class StatMbByRoleResource  {
 

	@Autowired
	@Qualifier("statMbByRoleDao")
	StatMbByRoleDao statMbByRoleDao;
	
	@Autowired
	@Qualifier("statMbByRoleExtDao")
	StatMbByRoleExtDao statMbByRoleExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-stat_mb_by_role@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<StatMbByRole> findAll () {
		List<StatMbByRole> r = new ArrayList<StatMbByRole>();
        List<StatMbByRole> l = statMbByRoleDao.searchPrototypeStatMbByRole(new StatMbByRole());
		for (StatMbByRole statMbByRole : l) {
			r.add(statMbByRole.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-stat_mb_by_role@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public StatMbByRole findById (@PathParam ("id") java.lang.String id) {
        StatMbByRole _statMbByRole = new StatMbByRole ();
		_statMbByRole.setId(id);
		_statMbByRole = statMbByRoleExtDao.getFirstStatMbByRole(_statMbByRole);
		if (_statMbByRole!=null) return _statMbByRole.flat();
		return new StatMbByRole ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") String id) {
        StatMbByRole statMbByRole = new StatMbByRole ();
        statMbByRole.setId(id);
        statMbByRoleDao.deleteStatMbByRole(statMbByRole);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public StatMbByRole create (
        @FormParam("id") String id,
        @FormParam("statMbPerCtryConfId") String statMbPerCtryConfId,
        @FormParam("roleName") String roleName,
        @FormParam("number") Long number,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        StatMbByRole _statMbByRole = new StatMbByRole (
           id,
           statMbPerCtryConfId,
           roleName,
           number);
        return save(_statMbByRole);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public StatMbByRole save(JAXBElement<StatMbByRole> jaxbStatMbByRole) {
        StatMbByRole statMbByRole = jaxbStatMbByRole.getValue();
        if (statMbByRole.getId()!=null)
            return statMbByRoleDao.updateStatMbByRole(statMbByRole);
        return save(statMbByRole);
    }

	public StatMbByRole save (StatMbByRole statMbByRole) {
		statMbByRoleDao.saveStatMbByRole(statMbByRole);
		return statMbByRole;
	}


}