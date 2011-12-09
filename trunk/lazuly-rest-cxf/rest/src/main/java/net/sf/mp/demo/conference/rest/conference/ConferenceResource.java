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

import net.sf.mp.demo.conference.dao.face.conference.ConferenceDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceExtDao;
import net.sf.mp.demo.conference.domain.conference.Conference;

/**
 *
 * <p>Title: ConferenceResource</p>
 *
 * <p>Description: remote interface for ConferenceResource service </p>
 *
 */
@Path ("/rest/xml/conferences")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class ConferenceResource  {
 

	@Autowired
	@Qualifier("conferenceDao")
	ConferenceDao conferenceDao;
	
	@Autowired
	@Qualifier("conferenceExtDao")
	ConferenceExtDao conferenceExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Conference> findAll () {
		List<Conference> r = new ArrayList<Conference>();
        List<Conference> l = conferenceDao.searchPrototypeConference(new Conference());
		for (Conference conference : l) {
			r.add(conference.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Conference findById (@PathParam ("id") java.lang.Long id) {
        Conference _conference = new Conference ();
		_conference.setId(id);
		_conference = conferenceExtDao.getFirstConference(_conference);
		if (_conference!=null) return _conference.flat();
		return new Conference ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Conference conference = new Conference ();
        conference.setId(id);
        conferenceDao.deleteConference(conference);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Conference create (
        @FormParam("id") Long id,
        @FormParam("name") String name,
        @FormParam("startDate") Date startDate,
        @FormParam("endDate") Date endDate,
        @FormParam("addressId") Long addressId,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Conference _conference = new Conference (
           id,
           name,
           startDate,
           endDate,
           addressId);
        return save(_conference);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Conference save(JAXBElement<Conference> jaxbConference) {
        Conference conference = jaxbConference.getValue();
        if (conference.getId()!=null)
            return conferenceDao.updateConference(conference);
        return save(conference);
    }

	public Conference save (Conference conference) {
		conferenceDao.saveConference(conference);
		return conference;
	}


}