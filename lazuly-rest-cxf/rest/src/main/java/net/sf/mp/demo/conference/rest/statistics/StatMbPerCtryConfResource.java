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

import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfDao;
import net.sf.mp.demo.conference.dao.face.statistics.StatMbPerCtryConfExtDao;
import net.sf.mp.demo.conference.domain.statistics.StatMbPerCtryConf;

/**
 *
 * <p>Title: StatMbPerCtryConfResource</p>
 *
 * <p>Description: remote interface for StatMbPerCtryConfResource service </p>
 *
 */
@Path ("/rest/xml/statmbperctryconfs")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class StatMbPerCtryConfResource  {
 

	@Autowired
	@Qualifier("statMbPerCtryConfDao")
	StatMbPerCtryConfDao statMbPerCtryConfDao;
	
	@Autowired
	@Qualifier("statMbPerCtryConfExtDao")
	StatMbPerCtryConfExtDao statMbPerCtryConfExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-stat_mb_per_ctry_conf@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<StatMbPerCtryConf> findAll () {
		List<StatMbPerCtryConf> r = new ArrayList<StatMbPerCtryConf>();
        List<StatMbPerCtryConf> l = statMbPerCtryConfDao.searchPrototypeStatMbPerCtryConf(new StatMbPerCtryConf());
		for (StatMbPerCtryConf statMbPerCtryConf : l) {
			r.add(statMbPerCtryConf.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-stat_mb_per_ctry_conf@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public StatMbPerCtryConf findById (@PathParam ("id") java.lang.String id) {
        StatMbPerCtryConf _statMbPerCtryConf = new StatMbPerCtryConf ();
		_statMbPerCtryConf.setId(id);
		_statMbPerCtryConf = statMbPerCtryConfExtDao.getFirstStatMbPerCtryConf(_statMbPerCtryConf);
		if (_statMbPerCtryConf!=null) return _statMbPerCtryConf.flat();
		return new StatMbPerCtryConf ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") String id) {
        StatMbPerCtryConf statMbPerCtryConf = new StatMbPerCtryConf ();
        statMbPerCtryConf.setId(id);
        statMbPerCtryConfDao.deleteStatMbPerCtryConf(statMbPerCtryConf);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public StatMbPerCtryConf create (
        @FormParam("id") String id,
        @FormParam("country") String country,
        @FormParam("conferenceName") String conferenceName,
        @FormParam("number") Long number,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        StatMbPerCtryConf _statMbPerCtryConf = new StatMbPerCtryConf (
           id,
           country,
           conferenceName,
           number);
        return save(_statMbPerCtryConf);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public StatMbPerCtryConf save(JAXBElement<StatMbPerCtryConf> jaxbStatMbPerCtryConf) {
        StatMbPerCtryConf statMbPerCtryConf = jaxbStatMbPerCtryConf.getValue();
        if (statMbPerCtryConf.getId()!=null)
            return statMbPerCtryConfDao.updateStatMbPerCtryConf(statMbPerCtryConf);
        return save(statMbPerCtryConf);
    }

	public StatMbPerCtryConf save (StatMbPerCtryConf statMbPerCtryConf) {
		statMbPerCtryConfDao.saveStatMbPerCtryConf(statMbPerCtryConf);
		return statMbPerCtryConf;
	}


}