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

import net.sf.mp.demo.conference.dao.face.conference.PresentationPlaceDao;
import net.sf.mp.demo.conference.dao.face.conference.PresentationPlaceExtDao;
import net.sf.mp.demo.conference.domain.conference.PresentationPlace;

/**
 *
 * <p>Title: PresentationPlaceResource</p>
 *
 * <p>Description: remote interface for PresentationPlaceResource service </p>
 *
 */
@Path ("/rest/xml/presentationplaces")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class PresentationPlaceResource  {
 

	@Autowired
	@Qualifier("presentationPlaceDao")
	PresentationPlaceDao presentationPlaceDao;
	
	@Autowired
	@Qualifier("presentationPlaceExtDao")
	PresentationPlaceExtDao presentationPlaceExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-presentation_place@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<PresentationPlace> findAll () {
		List<PresentationPlace> r = new ArrayList<PresentationPlace>();
        List<PresentationPlace> l = presentationPlaceDao.searchPrototypePresentationPlace(new PresentationPlace());
		for (PresentationPlace presentationPlace : l) {
			r.add(presentationPlace.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-presentation_place@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public PresentationPlace findById (@PathParam ("id") java.lang.Long id) {
        PresentationPlace _presentationPlace = new PresentationPlace ();
		_presentationPlace.setId(id);
		_presentationPlace = presentationPlaceExtDao.getFirstPresentationPlace(_presentationPlace);
		if (_presentationPlace!=null) return _presentationPlace.flat();
		return new PresentationPlace ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        PresentationPlace presentationPlace = new PresentationPlace ();
        presentationPlace.setId(id);
        presentationPlaceDao.deletePresentationPlace(presentationPlace);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PresentationPlace create (
        @FormParam("id") Long id,
        @FormParam("location") String location,
        @FormParam("numberOfSeats") Integer numberOfSeats,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        PresentationPlace _presentationPlace = new PresentationPlace (
           id,
           location,
           numberOfSeats);
        return save(_presentationPlace);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PresentationPlace save(JAXBElement<PresentationPlace> jaxbPresentationPlace) {
        PresentationPlace presentationPlace = jaxbPresentationPlace.getValue();
        if (presentationPlace.getId()!=null)
            return presentationPlaceDao.updatePresentationPlace(presentationPlace);
        return save(presentationPlace);
    }

	public PresentationPlace save (PresentationPlace presentationPlace) {
		presentationPlaceDao.savePresentationPlace(presentationPlace);
		return presentationPlace;
	}


}