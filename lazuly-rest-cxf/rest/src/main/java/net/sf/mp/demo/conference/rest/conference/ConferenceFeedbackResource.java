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

import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackDao;
import net.sf.mp.demo.conference.dao.face.conference.ConferenceFeedbackExtDao;
import net.sf.mp.demo.conference.domain.conference.ConferenceFeedback;

/**
 *
 * <p>Title: ConferenceFeedbackResource</p>
 *
 * <p>Description: remote interface for ConferenceFeedbackResource service </p>
 *
 */
@Path ("/rest/xml/conferencefeedbacks")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class ConferenceFeedbackResource  {
 

	@Autowired
	@Qualifier("conferenceFeedbackDao")
	ConferenceFeedbackDao conferenceFeedbackDao;
	
	@Autowired
	@Qualifier("conferenceFeedbackExtDao")
	ConferenceFeedbackExtDao conferenceFeedbackExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-conference_feedback@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<ConferenceFeedback> findAll () {
		List<ConferenceFeedback> r = new ArrayList<ConferenceFeedback>();
        List<ConferenceFeedback> l = conferenceFeedbackDao.searchPrototypeConferenceFeedback(new ConferenceFeedback());
		for (ConferenceFeedback conferenceFeedback : l) {
			r.add(conferenceFeedback.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-conference_feedback@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public ConferenceFeedback findById (@PathParam ("id") java.lang.Integer id) {
        ConferenceFeedback _conferenceFeedback = new ConferenceFeedback ();
		_conferenceFeedback.setId(id);
		_conferenceFeedback = conferenceFeedbackExtDao.getFirstConferenceFeedback(_conferenceFeedback);
		if (_conferenceFeedback!=null) return _conferenceFeedback.flat();
		return new ConferenceFeedback ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Integer id) {
        ConferenceFeedback conferenceFeedback = new ConferenceFeedback ();
        conferenceFeedback.setId(id);
        conferenceFeedbackDao.deleteConferenceFeedback(conferenceFeedback);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public ConferenceFeedback create (
        @FormParam("id") Integer id,
        @FormParam("comment") String comment,
        @FormParam("feedbackDate") Timestamp feedbackDate,
        @FormParam("conferenceId") Long conferenceId,
        @FormParam("conferenceMemberId") Long conferenceMemberId,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        ConferenceFeedback _conferenceFeedback = new ConferenceFeedback (
           id,
           comment,
           feedbackDate,
           conferenceId,
           conferenceMemberId);
        return save(_conferenceFeedback);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ConferenceFeedback save(JAXBElement<ConferenceFeedback> jaxbConferenceFeedback) {
        ConferenceFeedback conferenceFeedback = jaxbConferenceFeedback.getValue();
        if (conferenceFeedback.getId()!=null)
            return conferenceFeedbackDao.updateConferenceFeedback(conferenceFeedback);
        return save(conferenceFeedback);
    }

	public ConferenceFeedback save (ConferenceFeedback conferenceFeedback) {
		conferenceFeedbackDao.saveConferenceFeedback(conferenceFeedback);
		return conferenceFeedback;
	}


}