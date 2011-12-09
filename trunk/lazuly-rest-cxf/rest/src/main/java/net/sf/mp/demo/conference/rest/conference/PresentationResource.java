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

import net.sf.mp.demo.conference.dao.face.conference.PresentationDao;
import net.sf.mp.demo.conference.dao.face.conference.PresentationExtDao;
import net.sf.mp.demo.conference.domain.conference.Presentation;

/**
 *
 * <p>Title: PresentationResource</p>
 *
 * <p>Description: remote interface for PresentationResource service </p>
 *
 */
@Path ("/rest/xml/presentations")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class PresentationResource  {
 

	@Autowired
	@Qualifier("presentationDao")
	PresentationDao presentationDao;
	
	@Autowired
	@Qualifier("presentationExtDao")
	PresentationExtDao presentationExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-presentation@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Presentation> findAll () {
		List<Presentation> r = new ArrayList<Presentation>();
        List<Presentation> l = presentationDao.searchPrototypePresentation(new Presentation());
		for (Presentation presentation : l) {
			r.add(presentation.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-presentation@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Presentation findById (@PathParam ("id") java.lang.Long id) {
        Presentation _presentation = new Presentation ();
		_presentation.setId(id);
		_presentation = presentationExtDao.getFirstPresentation(_presentation);
		if (_presentation!=null) return _presentation.flat();
		return new Presentation ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Presentation presentation = new Presentation ();
        presentation.setId(id);
        presentationDao.deletePresentation(presentation);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Presentation create (
        @FormParam("id") Long id,
        @FormParam("startTime") Timestamp startTime,
        @FormParam("endTime") Timestamp endTime,
        @FormParam("abstractName") String abstractName,
        @FormParam("title") String title,
        @FormParam("status") String status,
        @FormParam("presentationPlaceId") Long presentationPlaceId,
        @FormParam("proposalTime") Timestamp proposalTime,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Presentation _presentation = new Presentation (
           id,
           startTime,
           endTime,
           abstractName,
           title,
           status,
           presentationPlaceId,
           proposalTime);
        return save(_presentation);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Presentation save(JAXBElement<Presentation> jaxbPresentation) {
        Presentation presentation = jaxbPresentation.getValue();
        if (presentation.getId()!=null)
            return presentationDao.updatePresentation(presentation);
        return save(presentation);
    }

	public Presentation save (Presentation presentation) {
		presentationDao.savePresentation(presentation);
		return presentation;
	}


}