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

import net.sf.mp.demo.conference.dao.face.conference.SpeakerDao;
import net.sf.mp.demo.conference.dao.face.conference.SpeakerExtDao;
import net.sf.mp.demo.conference.domain.conference.Speaker;

/**
 *
 * <p>Title: SpeakerResource</p>
 *
 * <p>Description: remote interface for SpeakerResource service </p>
 *
 */
@Path ("/rest/xml/speakers")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class SpeakerResource  {
 

	@Autowired
	@Qualifier("speakerDao")
	SpeakerDao speakerDao;
	
	@Autowired
	@Qualifier("speakerExtDao")
	SpeakerExtDao speakerExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-speaker@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Speaker> findAll () {
		List<Speaker> r = new ArrayList<Speaker>();
        List<Speaker> l = speakerDao.searchPrototypeSpeaker(new Speaker());
		for (Speaker speaker : l) {
			r.add(speaker.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-speaker@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Speaker findById (@PathParam ("id") java.lang.Long id) {
        Speaker _speaker = new Speaker ();
		_speaker.setId(id);
		_speaker = speakerExtDao.getFirstSpeaker(_speaker);
		if (_speaker!=null) return _speaker.flat();
		return new Speaker ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Speaker speaker = new Speaker ();
        speaker.setId(id);
        speakerDao.deleteSpeaker(speaker);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Speaker create (
        @FormParam("id") Long id,
        @FormParam("conferenceMemberId") Long conferenceMemberId,
        @FormParam("bio") String bio,
        @FormParam("photo") String photo,
        @FormParam("webSiteUrl") String webSiteUrl,
        @FormParam("sponsorId") Long sponsorId,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Speaker _speaker = new Speaker (
           id,
           conferenceMemberId,
           bio,
           photo,
           webSiteUrl,
           sponsorId);
        return save(_speaker);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Speaker save(JAXBElement<Speaker> jaxbSpeaker) {
        Speaker speaker = jaxbSpeaker.getValue();
        if (speaker.getId()!=null)
            return speakerDao.updateSpeaker(speaker);
        return save(speaker);
    }

	public Speaker save (Speaker speaker) {
		speakerDao.saveSpeaker(speaker);
		return speaker;
	}


}