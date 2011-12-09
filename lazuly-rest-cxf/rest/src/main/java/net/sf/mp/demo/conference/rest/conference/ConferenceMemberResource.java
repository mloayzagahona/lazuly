package net.sf.mp.demo.conference.rest.conference;


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

import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceMemberExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceMember;

/**
 *
 * <p>Title: ConferenceMemberResource</p>
 *
 * <p>Description: remote interface for ConferenceMemberResource service </p>
 *
 */
@Path ("/rest/xml/conferencemembers")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class ConferenceMemberResource  {
 

	@Autowired
	@Qualifier("conferenceMemberDao")
	ConferenceMemberDao conferenceMemberDao;
	
	@Autowired
	@Qualifier("conferenceMemberExtDao")
	ConferenceMemberExtDao conferenceMemberExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference_member@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<ConferenceMember> findAll () {
		List<ConferenceMember> r = new ArrayList<ConferenceMember>();
        List<ConferenceMember> l = conferenceMemberDao.searchPrototypeConferenceMember(new ConferenceMember());
		for (ConferenceMember conferenceMember : l) {
			r.add(conferenceMember.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference_member@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public ConferenceMember findById (@PathParam ("id") java.lang.Long id) {
        ConferenceMember _conferenceMember = new ConferenceMember ();
		_conferenceMember.setId(id);
		_conferenceMember = conferenceMemberExtDao.getFirstConferenceMember(_conferenceMember);
		if (_conferenceMember!=null) return _conferenceMember.flat();
		return new ConferenceMember ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        ConferenceMember conferenceMember = new ConferenceMember ();
        conferenceMember.setId(id);
        conferenceMemberDao.deleteConferenceMember(conferenceMember);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ConferenceMember create (
        @FormParam("id") Long id,
        @FormParam("conferenceId") Long conferenceId,
        @FormParam("firstName") String firstName,
        @FormParam("lastName") String lastName,
        @FormParam("email") String email,
        @FormParam("addressId") Long addressId,
        @FormParam("status") String status,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceMember _conferenceMember = new ConferenceMember (
           id,
           conferenceId,
           firstName,
           lastName,
           email,
           addressId,
           status);
        return save(_conferenceMember);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConferenceMember save(JAXBElement<ConferenceMember> jaxbConferenceMember) {
        ConferenceMember conferenceMember = jaxbConferenceMember.getValue();
        if (conferenceMember.getId()!=null)
            return conferenceMemberDao.updateConferenceMember(conferenceMember);
        return save(conferenceMember);
    }

	public ConferenceMember save (ConferenceMember conferenceMember) {
		conferenceMemberDao.saveConferenceMember(conferenceMember);
		return conferenceMember;
	}


}