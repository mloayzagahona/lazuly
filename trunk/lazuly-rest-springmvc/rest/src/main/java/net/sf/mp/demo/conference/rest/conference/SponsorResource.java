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
@RequestMapping ("/rest/xml/sponsors")
@Controller 
public class SponsorResource  {

	@Autowired
	@Qualifier("sponsorDao")
	SponsorDao sponsorDao;
	
	@Autowired
	@Qualifier("sponsorExtDao")
	SponsorExtDao sponsorExtDao;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @FIND_ALL-sponsor@
    @RequestMapping(method = RequestMethod.GET)
    @Transactional
	@ResponseBody
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
    public Sponsor findById (@PathVariable ("id") java.lang.Long id) {
        Sponsor _sponsor = new Sponsor ();
		_sponsor.setId(id);
		_sponsor = sponsorExtDao.getFirstSponsor(_sponsor);
		if (_sponsor!=null) return _sponsor.flat();
		return new Sponsor ();
    }
//MP-MANAGED-UPDATABLE-ENDING

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
	public Sponsor create (
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("privilegeType") String privilegeType,
        @RequestParam("status") String status,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("addressId") Long addressId,
        HttpServletResponse servletResponse
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@ResponseBody
    public String delete (@PathVariable ("id") Long id) {
        Sponsor sponsor = new Sponsor ();
        sponsor.setId(id);
        sponsorDao.deleteSponsor(sponsor);
		return "OK";
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public Sponsor save (
        @RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("privilegeType") String privilegeType,
        @RequestParam("status") String status,
        @RequestParam("conferenceId") Long conferenceId,
        @RequestParam("addressId") Long addressId,
        HttpServletResponse servletResponse
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


	public Sponsor save (Sponsor sponsor) {
		sponsorDao.saveSponsor(sponsor);
		return sponsor;
	}



}