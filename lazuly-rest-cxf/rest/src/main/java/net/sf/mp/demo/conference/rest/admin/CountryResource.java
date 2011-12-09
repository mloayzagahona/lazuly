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

import net.sf.mp.demo.conference.dao.face.admin.CountryDao;
import net.sf.mp.demo.conference.dao.face.admin.CountryExtDao;
import net.sf.mp.demo.conference.domain.admin.Country;

/**
 *
 * <p>Title: CountryResource</p>
 *
 * <p>Description: remote interface for CountryResource service </p>
 *
 */
@Path ("/rest/xml/countries")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class CountryResource  {
 

	@Autowired
	@Qualifier("countryDao")
	CountryDao countryDao;
	
	@Autowired
	@Qualifier("countryExtDao")
	CountryExtDao countryExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-country@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Country> findAll () {
		List<Country> r = new ArrayList<Country>();
        List<Country> l = countryDao.searchPrototypeCountry(new Country());
		for (Country country : l) {
			r.add(country.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-country@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Country findById (@PathParam ("id") java.lang.Integer id) {
        Country _country = new Country ();
		_country.setId(id);
		_country = countryExtDao.getFirstCountry(_country);
		if (_country!=null) return _country.flat();
		return new Country ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Integer id) {
        Country country = new Country ();
        country.setId(id);
        countryDao.deleteCountry(country);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Country create (
        @FormParam("id") Integer id,
        @FormParam("name") String name,
        @FormParam("isoName") String isoName,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Country _country = new Country (
           id,
           name,
           isoName);
        return save(_country);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Country save(JAXBElement<Country> jaxbCountry) {
        Country country = jaxbCountry.getValue();
        if (country.getId()!=null)
            return countryDao.updateCountry(country);
        return save(country);
    }

	public Country save (Country country) {
		countryDao.saveCountry(country);
		return country;
	}


}