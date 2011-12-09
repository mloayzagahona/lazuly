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

import net.sf.mp.demo.conference.dao.face.conference.AddressDao;
import net.sf.mp.demo.conference.dao.face.conference.AddressExtDao;
import net.sf.mp.demo.conference.domain.conference.Address;

/**
 *
 * <p>Title: AddressResource</p>
 *
 * <p>Description: remote interface for AddressResource service </p>
 *
 */
@Path ("/rest/xml/addresses")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Service
@Transactional
public class AddressResource  {
 

	@Autowired
	@Qualifier("addressDao")
	AddressDao addressDao;
	
	@Autowired
	@Qualifier("addressExtDao")
	AddressExtDao addressExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-address@
    @GET
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    public List<Address> findAll () {
		List<Address> r = new ArrayList<Address>();
        List<Address> l = addressDao.searchPrototypeAddress(new Address());
		for (Address address : l) {
			r.add(address.flat());
		}
		return r;
    }
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_BY_ID-address@
    @GET
    @Path("{id}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})		
    public Address findById (@PathParam ("id") java.lang.Long id) {
        Address _address = new Address ();
		_address.setId(id);
		_address = addressExtDao.getFirstAddress(_address);
		if (_address!=null) return _address.flat();
		return new Address ();
    }
//MP-MANAGED-UPDATABLE-ENDING

    @DELETE
    @Path("{id}")
    public void delete (@PathParam ("id") Long id) {
        Address address = new Address ();
        address.setId(id);
        addressDao.deleteAddress(address);
    }

    @POST
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Address create (
        @FormParam("id") Long id,
        @FormParam("street1") String street1,
        @FormParam("street2") String street2,
        @FormParam("countryId") Integer countryId,
        @Context HttpServletResponse servletResponse
        ) throws IOException {
        Address _address = new Address (
           id,
           street1,
           street2,
           countryId);
        return save(_address);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Address save(JAXBElement<Address> jaxbAddress) {
        Address address = jaxbAddress.getValue();
        if (address.getId()!=null)
            return addressDao.updateAddress(address);
        return save(address);
    }

	public Address save (Address address) {
		addressDao.saveAddress(address);
		return address;
	}


}