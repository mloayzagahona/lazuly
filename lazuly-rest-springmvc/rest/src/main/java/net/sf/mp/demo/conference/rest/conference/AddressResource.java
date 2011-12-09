package net.sf.mp.demo.conference.rest.conference;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


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
@RequestMapping ("/rest/xml/addresses")
@Controller 
public class AddressResource  {

	@Autowired
	@Qualifier("addressDao")
	AddressDao addressDao;
	
	@Autowired
	@Qualifier("addressExtDao")
	AddressExtDao addressExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-address@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Address findById (@PathVariable ("id") java.lang.Long id) {
        Address _address = new Address ();
		_address.setId(id);
		_address = addressExtDao.getFirstAddress(_address);
		if (_address!=null) return _address.flat();
		return new Address ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Address create (
        @RequestParam("id") Long id,
        @RequestParam("street1") String street1,
        @RequestParam("street2") String street2,
        @RequestParam("countryId") Integer countryId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Address _address = new Address (
           id,
           street1,
           street2,
           countryId);
        return save(_address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Address address = new Address ();
        address.setId(id);
        addressDao.deleteAddress(address);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Address save (
        @RequestParam("id") Long id,
        @RequestParam("street1") String street1,
        @RequestParam("street2") String street2,
        @RequestParam("countryId") Integer countryId,
        HttpServletResponse servletResponse
        ) throws IOException {
        Address _address = new Address (
           id,
           street1,
           street2,
           countryId);
        return save(_address);
    }	


	public Address save (Address address) {
		addressDao.saveAddress(address);
		return address;
	}



}