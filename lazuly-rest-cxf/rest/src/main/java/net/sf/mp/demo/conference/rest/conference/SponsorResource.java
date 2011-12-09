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

import net.sf.mp.demo.conference.dao.face.conference.SponsorDao;
import net.sf.mp.demo.conference.dao.face.conference.SponsorExtDao;
import net.sf.mp.demo.conference.domain.conference.Sponsor;

/**
 *
 * <p>Title: SponsorResource</p>
 *
 * <p>Description: remote interface for SponsorResource service </p>
 *
 */
@Path ("/rest/xml/sponsors")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class SponsorResource  {
 

	@Autowired
	@Qualifier("sponsorDao")
	SponsorDao sponsorDao;
	
	@Autowired
	@Qualifier("sponsorExtDao")
	SponsorExtDao sponsorExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-sponsor@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Sponsor> findAll () {
		List<Sponsor> r = new ArrayList<Sponsor>();
        List<Sponsor> l = sponsorDao.searchPrototypeSponsor(new Sponsor());
		for (Sponsor sponsor : l) {
			r.add(sponsor.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-sponsor@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Sponsor findById (@PathParam ("id") java.lang.Long id) {
        Sponsor _sponsor = new Sponsor ();
		_sponsor.setId(id);
		_sponsor = sponsorExtDao.getFirstSponsor(_sponsor);
		if (_sponsor!=null) return _sponsor.flat();
		return new Sponsor ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Sponsor sponsor = new Sponsor ();
        sponsor.setId(id);
        sponsorDao.deleteSponsor(sponsor);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Sponsor create (
        @FormParam("id") Long id,
        @FormParam("name") String name,
        @FormParam("privilegeType") String privilegeType,
        @FormParam("status") String status,
        @FormParam("conferenceId") Long conferenceId,
        @FormParam("addressId") Long addressId,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Sponsor _sponsor = new Sponsor (
           id,
           name,
           privilegeType,
           status,
           conferenceId,
           addressId);
        return save(_sponsor);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sponsor save(JAXBElement<Sponsor> jaxbSponsor) {
        Sponsor sponsor = jaxbSponsor.getValue();
        if (sponsor.getId()!=null)
            return sponsorDao.updateSponsor(sponsor);
        return save(sponsor);
    }

	public Sponsor save (Sponsor sponsor) {
		sponsorDao.saveSponsor(sponsor);
		return sponsor;
	}


}