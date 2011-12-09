package net.sf.mp.demo.conference.rest.admin;


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
@RequestMapping ("/rest/xml/countries")
@Controller 
public class CountryResource  {

	@Autowired
	@Qualifier("countryDao")
	CountryDao countryDao;
	
	@Autowired
	@Qualifier("countryExtDao")
	CountryExtDao countryExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-country@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Country findById (@PathVariable ("id") java.lang.Integer id) {
        Country _country = new Country ();
		_country.setId(id);
		_country = countryExtDao.getFirstCountry(_country);
		if (_country!=null) return _country.flat();
		return new Country ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Country create (
        @RequestParam("id") Integer id,
        @RequestParam("name") String name,
        @RequestParam("isoName") String isoName,
        HttpServletResponse servletResponse
        ) throws IOException {
        Country _country = new Country (
           id,
           name,
           isoName);
        return save(_country);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Integer id) {
        Country country = new Country ();
        country.setId(id);
        countryDao.deleteCountry(country);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Country save (
        @RequestParam("id") Integer id,
        @RequestParam("name") String name,
        @RequestParam("isoName") String isoName,
        HttpServletResponse servletResponse
        ) throws IOException {
        Country _country = new Country (
           id,
           name,
           isoName);
        return save(_country);
    }	


	public Country save (Country country) {
		countryDao.saveCountry(country);
		return country;
	}



}